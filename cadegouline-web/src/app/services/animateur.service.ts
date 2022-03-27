import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { ToastrService } from 'ngx-toastr';

const httpOptions = {
    headers: new HttpHeaders({
        Authorization: 'my-auth-token'
    })
};


@Injectable()
export class AnimateurService {

    constructor(private httpClient: HttpClient, private toastr: ToastrService) {

    }

    saveTrack(file: File, track: string) {
        const formData = new FormData();
        formData.append("fileTrack", file);
        formData.append("track", track);
        var environement = window.location.hostname

        if (environement == "localhost"){
            environement = "://" + environement + ":3333";
        }
        else {
            environement = "s://" + environement
        }

        const url = "http" + environement + "/api/v1/track/insert"
        console.log(url);
        this.httpClient.post<string>(url, formData, httpOptions).subscribe(
            () => {

            },
            (error) => {
                if (error.status == 200) {
                    this.toastr.success("Le fichier a bien été enregistré ! ", "Succès !");
                } else {
                    this.toastr.error("Une erreur est survenue", "Erreur");
                }
            }
        );
    }

}