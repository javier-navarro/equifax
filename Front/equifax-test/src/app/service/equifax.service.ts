import { ResponseCoordenadas } from './../models/coordenadas.interface';
import { ResponseInterface } from './../models/response.interface';
import { LoginInterface } from './../models/login.interface';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { identifierModuleUrl } from '@angular/compiler';

@Injectable({
    providedIn: 'root'
})
export class EquifaxService {

    url:string = "equifax-test/";
    

    constructor(
        private readonly http: HttpClient) { }


    public login (form:LoginInterface) :Observable<ResponseInterface> {
        let direccion = this.url+"auth/login";
        return this.http.post<ResponseInterface>(direccion,form);
    }

    public listaCoordenadas(token){

        const headers = new HttpHeaders()
            .set("token", token);

        let direccion = this.url+"listado"
        return this.http.get(direccion,{headers});
    }

    public listaCoordenadasId(token,id){

        const headers = new HttpHeaders()
            .set("token", token);

        let direccion = this.url+"listado?idCoordenada="+id;
        return this.http.get(direccion,{headers});
    }

}