package com.sevi.cadegouline.services;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sevi.cadegouline.entities.Track;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class PlaylistService {
    /*
    @Scheduled(cron="0 * * * * *", zone="Europe/Paris")
    */
    public List<Track> generatePlaylist() throws InvalidDataException, UnsupportedTagException, IOException {
        int nbFiles = 0;
        File baseDir = new File("C:\\Users\\sebas\\Documents\\workspace\\Sons_Phiphi");
        File sqlFile = new File("C:\\Users\\sebas\\Documents\\workspace\\Sons_Phiphi\\insertTracks.sql");
        String type;
        StringBuilder sb = new StringBuilder();
        StringBuilder subSb = new StringBuilder();
        sb.append("insert into cadegouline.track (album, artist, counter, is_blocked, old_or_new, path_to_file, title) \n" +
            "values ");

        if (baseDir.isDirectory()) {
            for (File subDir : baseDir.listFiles()) {
                if (subDir.isDirectory()) {
                    type = subDir.getName().substring(0, subDir.getName().indexOf('-'));
                    for (File subSubDir : subDir.listFiles()) {
                        if (subSubDir.isDirectory()) {
                            for (File file : subSubDir.listFiles()) {
                                nbFiles++;
                                try {
                                    if (!type.equals("Jingles")) {
                                        Mp3File mp3File = new Mp3File(file);
                                        subSb = new StringBuilder();

                                        if (mp3File.hasId3v1Tag()) {
                                            subSb.append("(");
                                            subSb.append("\"" + mp3File.getId3v1Tag().getAlbum().replace("'", "''").replace("\"", "\\\"") + "\"");
                                            //System.out.print(mp3File.getId3v1Tag().getAlbum());
                                            subSb.append(",");
                                            //System.out.print(",");
                                            subSb.append("\"" + mp3File.getId3v1Tag().getArtist().replace("'", "''").replace("\"", "\\\"") + "\"");
                                            //System.out.print(mp3File.getId3v1Tag().getArtist());
                                            subSb.append(",0, false");
                                            oldOrNew(type, subSb, mp3File);

                                            subSb.append("\"" + mp3File.getId3v1Tag().getTitle().replace("'", "''").replace("\"", "\\\"") + "\"");
                                            //System.out.print(mp3File.getId3v1Tag().getTitle());

                                            subSb.append("),\n");
                                            //System.out.println("");
                                        } else if (mp3File.hasId3v2Tag()){
                                            subSb.append("(");
                                            subSb.append("\"" + mp3File.getId3v2Tag().getAlbum().replace("'", "''").replace("\"", "\\\"") + "\"");
                                            //System.out.print(mp3File.getId3v2Tag().getAlbum());
                                            subSb.append(",");
                                            //System.out.print(",");
                                            subSb.append("\"" + mp3File.getId3v2Tag().getArtist().replace("'", "''").replace("\"", "\\\"") + "\"");
                                            //System.out.print(mp3File.getId3v2Tag().getArtist());
                                            subSb.append(",0, false");

                                            oldOrNew(type, subSb, mp3File);

                                            subSb.append("\"" + mp3File.getId3v2Tag().getTitle().replace("'", "''").replace("\"", "\\\"") + "\"");
                                            //System.out.print(mp3File.getId3v2Tag().getTitle());

                                            //System.out.println("");
                                            subSb.append("),\n");
                                        }
                                        if (!subSb.toString().contains("null,null") && (!subSb.toString().contains("(,"))) {
                                            sb.append(subSb);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(sb);
        /*
        FileOutputStream outputStream = new FileOutputStream(sqlFile);
        byte[] strToBytes = sb.toString().getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
        */

        System.out.println(nbFiles);
        return new ArrayList<>();
    }

    private void oldOrNew(String type, StringBuilder sb, Mp3File mp3File) {
        if (type.equals("Anciens")) {
            sb.append(",\"old\",");
            sb.append("\"" + mp3File.getFilename().substring("C:\\Users\\sebas\\Documents\\workspace\\Sons_Phiphi\\Anciens-20211128T155248Z-001\\Anciens\\".length()).replace("'", "''").replace("\"", "\\\""));
            sb.append("\",");
            //System.out.print(",old,");
        } else {
            sb.append(",\"new\",");
            sb.append("\"" + mp3File.getFilename().substring("C:\\Users\\sebas\\Documents\\workspace\\Sons_Phiphi\\Nouveautés-20210831T131157Z-003\\Nouveautés\\".length() ).replace("'", "''").replace("\"", "\\\""));
            sb.append("\",");
            //System.out.print(",new,");
        }

    }
}
