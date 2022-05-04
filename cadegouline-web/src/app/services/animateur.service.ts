import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class AnimateurService {

    constructor(private httpClient: HttpClient, private toastr: ToastrService) {

    }

    saveTrack(file: File, track: string)  {
        if (file.name.lastIndexOf(".mp3") == file.name.length - 4 ) {
            const formData = new FormData();
            formData.append("fileTrack", file);
            formData.append("track", track);
            let environment = window.location.hostname
            environment = "://" + environment + ":3333";
            const url = "http" + environment + "/api/v1/track/insert"
            //console.log(url);
            this.httpClient.post(url, formData, {responseType: 'text'}).subscribe(
                () => {
                    this.toastr.success("Le fichier a bien été enregistré ! ", "Succès !");
                },
                (error: HttpErrorResponse) => {
                    if (error.status == 201) {
                        this.toastr.success("Le fichier a bien été enregistré ! ", "Succès !");
                    } else {
                        if (error.error == "Le fichier n'a pas une extension correcte") {
                            this.toastr.error("Le fichier n'est pas correct.", "Erreur !");
                        } else {
                            this.toastr.error("Une erreur est survenue", "Erreur");
                        }
                    }
                }
            )
        } else {
            this.toastr.error("Le fichier n'est pas correct.", "Erreur !");
        }

    }

}