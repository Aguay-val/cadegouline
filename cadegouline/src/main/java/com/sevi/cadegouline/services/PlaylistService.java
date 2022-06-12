package com.sevi.cadegouline.services;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sevi.cadegouline.entities.Program;
import com.sevi.cadegouline.entities.Track;
import com.sevi.cadegouline.repositories.JingleRepository;
import com.sevi.cadegouline.repositories.ProgramRepository;
import com.sevi.cadegouline.repositories.TrackRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class PlaylistService {

    @Value("${spring.config.additional-location}")
    private String fileDir;
    private final TrackRepository trackRepository;
    private final JingleRepository jingleRepository;
    private final ProgramRepository programRepository;


    public PlaylistService(TrackRepository trackRepository, JingleRepository jingleRepository, ProgramRepository programRepository) {
        this.trackRepository = trackRepository;
        this.jingleRepository = jingleRepository;
        this.programRepository = programRepository;
    }


    //@Scheduled(cron="*/30 * * * * ?")
    public void executePlaylist() throws InvalidDataException, UnsupportedTagException, IOException {
        generatePlayList();
    }

    @Scheduled(cron="0 0 19 * * ?", zone="Europe/Paris")
    public void generatePlayList() throws InvalidDataException, UnsupportedTagException, IOException {

        List<Long> allTracksAvailable = trackRepository.findAllIds();
        List<String> playlistGenerated = new ArrayList<>();

        String pathProgram = getProgramOfTheDay();
        playlistGenerated.add(pathProgram);

        Mp3File programFile = new Mp3File(pathProgram);

        Long counter = programFile.getLengthInMilliseconds();

        int addJingle = -1;

        int newPosition = 0;
        List<String> positions;
        //Tant que la playlist ne dure pas 24h
        while (counter < 86400000 && allTracksAvailable.size() > 0) {
            if (addJingle == 2) {
                addJingle = 0;
                playlistGenerated.add(chooseJingle());
            } else {
                addJingle++;
            }

            newPosition = (int) Math.floor(Math.random() * 3);
            positions = new ArrayList<>();
            if (newPosition == 0) {
                positions.add("new");
                positions.add("old");
                positions.add("old");
            } else if (newPosition == 1) {
                positions.add("old");
                positions.add("new");
                positions.add("old");
            } else {
                positions.add("old");
                positions.add("old");
                positions.add("new");
            }
            try {
                Pair<List<Track>, Long> choosen = chooseListTrack(positions, allTracksAvailable, counter);
                for (Track track : choosen.getFirst()) {
                    playlistGenerated.add(fileDir + track.getPathToFile());
                    allTracksAvailable.remove(track.getId());
                }
                counter = choosen.getSecond();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        System.out.println("counter = " + counter + "; alltrackavailable.size() = " + allTracksAvailable.size());
        System.out.println("Track restants : ");
        int countOld = 0;
        int countNew = 0;
        for(Long id : allTracksAvailable) {
            Track current = trackRepository.getById(id);
            if (current.getOldOrNew().equals("old")) {
                countOld++;
            } else {
                countNew++;
            }

        }


        StringBuilder sb = new StringBuilder();
        for (String file : playlistGenerated) {
            //on enleve l'extension ".mp3"
            sb.append(file.substring(0, file.length() - 3 ));
            //.. pour la remplacer par .ogg
            sb.append("ogg");
            sb.append("\n");
        }

        File playlistFile = new File(fileDir + "playlist.txt");
        Date yesterday = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String yesterdayString = formatter.format(yesterday);

        String archivePath = fileDir.replace("medias", "media");
        archivePath += "playlist_" + yesterdayString + ".txt";
        Files.copy(Path.of(playlistFile.getAbsolutePath()), Path.of(archivePath));

        FileOutputStream outputStream = new FileOutputStream(playlistFile);
        byte[] strToBytes = sb.toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();

    }

    private String getProgramOfTheDay() {

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if ((weekOfYear % 2 ) == 1) {
            dayOfWeek += 7;
        }

        Program p = programRepository.findProgramOfTheDay(dayOfWeek - 1);
        System.out.println(fileDir + p.getPathToFile());
        return fileDir + p.getPathToFile();
    }

    private Pair<List<Track>, Long> chooseListTrack(List<String> positions, List<Long> idTrackList, Long counter) throws Exception {
        List<Track> listTrack = new ArrayList<>();
        Track currentTrack;
        Mp3File currentFile;
        for (String tag : positions) {
            currentTrack = chooseTrack(tag, idTrackList) ;
            listTrack.add(currentTrack);
            try {
                currentFile = new Mp3File(fileDir + currentTrack.getPathToFile());
                counter += currentFile.getLengthInMilliseconds();
            } catch (Exception e ) {
                System.out.println(e.getMessage() + " for " + currentTrack.getPathToFile());
            }
        }

        return Pair.of(listTrack, counter) ;
    }

    private Track chooseTrack(String oldOrNew, List<Long> allTrackIds) throws Exception {

        Boolean isOk = false;
        Boolean impossibleToContinue = false;
        Track track;

        while (!isOk && !impossibleToContinue) {
            impossibleToContinue = (trackRepository.countOld(allTrackIds) < 2) || (trackRepository.countNew(allTrackIds) < 1);

            int position = (int) Math.floor(Math.random() * allTrackIds.size());
            track = trackRepository.getById(allTrackIds.get(position));
            try {
                isOk = track.getOldOrNew().equals(oldOrNew);
            } catch (Exception e){
                isOk = false;
            }

            if (isOk){
                //System.out.println("Choose track : " + "id = " + track.getId() + "; title = " + track.getPathToFile()) ;
                if (track.getCounter() == null) {
                    track.setCounter(1);
                } else {
                    track.setCounter(track.getCounter() + 1);
                }
                trackRepository.save(track);
                return track;
            }
        }

        if (impossibleToContinue) {
            throw new Exception("Pas assez de track");
        }

        return new Track();
    }

    private String chooseJingle() {
        List<Long> jingleIds = jingleRepository.findAllIds();
        int position = (int) Math.floor(Math.random() * jingleIds.size());
        //System.out.println("Jingle : " + fileDir + jingleRepository.getById(jingleIds.get(position)).getPathToFile());
        return fileDir + jingleRepository.getById(jingleIds.get(position)).getPathToFile();
    }

}
