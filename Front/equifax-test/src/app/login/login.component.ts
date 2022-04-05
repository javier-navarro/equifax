import { ResponseInterface } from './../models/response.interface';
import { EquifaxService } from './../service/equifax.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  submit() {
    if (this.form.valid) {
      this.submitEM.emit(this.form.value);
    }
  }
  
  @Input() error: string | null;

  @Output() submitEM = new EventEmitter();

  constructor(private equifaxService: EquifaxService, private router:Router ) { 
  }

  ngOnInit() {
    localStorage.removeItem('token');
  }

  
  login(){
    this.equifaxService.login(this.form.value).subscribe(data=>{
      let dataResponse : ResponseInterface = data;
      localStorage.setItem('token',dataResponse.token)
      this.error = null;
      this.router.navigate(['coordenadas']);
    },error => {
      this.error = "Username o password inválido, reintente.";
    });
  }
  

}
