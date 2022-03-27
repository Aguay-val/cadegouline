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
        let environment = window.location.hostname
        environment = "://" + environment + ":3333";
        const url = "http" + environment + "/api/v1/track/insert"
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