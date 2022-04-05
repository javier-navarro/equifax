
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { EquifaxService } from '../service/equifax.service';
import {  FormControl, FormGroup } from '@angular/forms';
import { FnParam } from '@angular/compiler/src/output/output_ast';
import { isObject } from 'util';


export interface PeriodicElement {
  ID: number;
  latitud: string;
  longitud: string;
  datos: string;
}

@Component({
  selector: 'app-coordenadas',
  templateUrl: './coordenadas.component.html',
  styleUrls: ['./coordenadas.component.css']
})
export class CoordenadasComponent implements OnInit {

  listax : any;
  form: FormGroup = new FormGroup({
    latitud: new FormControl(''),
    longitud: new FormControl(''),
  });
  displayedColumns: string[] = ['ID', 'latitud', 'longitud', 'datos'];

  selectedValue: string;
  deshabilitaLongitud:boolean =false;
  deshabilitaLatitud:boolean =false;

  @Input() error: string | null;

  @Output() submitEM = new EventEmitter();

  constructor(private equifaxService: EquifaxService,
    private router:Router) {
      
  }

  ngOnInit() {
    let token = localStorage.getItem('token');

    if(token == "" || token == null){
      this.router.navigate(['login']);
    }
    this.getCoordenadas();

  }

  getCoordenadas(){
    let token = localStorage.getItem('token');
    this.equifaxService.listaCoordenadas(token).subscribe(
      data=>{
        this.listax = data;
      },error =>{
        console.log(error);
      }
    )
  }

  buscar(){
    if (this.form.valid) {
      this.submitEM.emit(this.form.value);
    }
    let token = localStorage.getItem('token');

    

    if(this.form.value.latitud !==""){
      this.equifaxService.listaCoordenadasId(token,this.form.value.latitud).subscribe(
        data=>{
          this.listax = data;
        },error =>{
          console.log(error);
        }
      )
    }
    if(this.form.value.longitud !==""){
      this.equifaxService.listaCoordenadasId(token,this.form.value.longitud).subscribe(
        data=>{
          this.listax = data;
        },error =>{
          console.log(error);
        }
      )
    }
  }

  deshabilitarLongitud(){
    if(this.form.value.latitud != null){
      this.deshabilitaLongitud = true;
    }
    if(this.form.value.latitud === undefined){
      this.deshabilitaLongitud = false;
      this.getCoordenadas();
    }
  }

  deshabilitarLatitud(){
    if(this.form.value.longitud != null){
      this.deshabilitaLatitud = true;
    }
    if(this.form.value.longitud === undefined){
      this.deshabilitaLatitud = false;
      this.getCoordenadas();
    }
  }

  

}
