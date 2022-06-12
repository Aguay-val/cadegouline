import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class ProgramService {

    constructor(private httpClient: HttpClient, private toastr: ToastrService) {

    }

    saveProgram(file: File, program: string)  {
        if (file.name.toLowerCase().lastIndexOf(".mp3") == file.name.length - 4 ) {
            const formData = new FormData();
            formData.append("fileProgram", file);
            formData.append("program", program);
            let environment = window.location.hostname
            environment = "://" + environment + ":3333";
            const url = "http" + environment + "/api/v1/program/insert"
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