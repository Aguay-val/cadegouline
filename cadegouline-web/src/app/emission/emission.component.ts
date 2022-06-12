import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Program} from "../models/Program.model";
import {ProgramService} from "../services/program.service";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule} from "@angular/material/core";
import {
    MAT_MOMENT_DATE_FORMATS,
    MomentDateAdapter,
    MAT_MOMENT_DATE_ADAPTER_OPTIONS,
} from '@angular/material-moment-adapter';

@Component({
    selector: 'app-emission',
    templateUrl: './emission.component.html',
    styleUrls: ['./emission.component.css'],
    providers: [
        {provide: MAT_DATE_LOCALE, useValue: 'fr'},
        {
            provide: DateAdapter,
            useClass: MomentDateAdapter,
            deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
        },
        {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS}
    ]
})
export class EmissionComponent implements OnInit {

    title: string;
    programForm: FormGroup;
    file: File;
    fileArray: Promise<ArrayBuffer>;
    program: Program;
    picker: MatDatepickerModule;

    constructor(private programService: ProgramService) { }

    ngOnInit(): void {
        this.programForm = new FormGroup({
            title: new FormControl(null, [
                Validators.required
            ]),
            date: new FormControl(null, [
                Validators.required
            ]),
            file: new FormControl(null, [
                Validators.required
            ])
        })
    }
    onFileSelected(event) {
        this.file = event.target.files[0]
        this.fileArray = this.file.arrayBuffer()
    }


    onSubmit(): void {
        this.program = new Program(
            this.programForm.value.title,
            this.programForm.value.date,
            this.file.name
        );
        const jsonTrack = JSON.stringify(this.program);

        this.programService.saveProgram(this.file, jsonTrack);
        this.programForm.value.title = "";
        this.title = "";
    }

}
