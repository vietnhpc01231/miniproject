import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  AbstractControl,
  FormBuilder,
} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppServiceService } from '../service/app-service.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  
  constructor(private appService:AppServiceService,private router:Router,private cookieService:CookieService) { }

  public formLogin = new FormGroup({
    username: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required,])
  })

  ngOnInit(): void {
  }

  login(){
    console.log(this.formLogin.value);
    this.appService.login(this.formLogin.value).subscribe(data=>{
      console.log(data)
      this.cookieService.set('auth',data.token);
      document.location.href='/product'
    },e=>{
      console.log(e)
      if(e.status==503){
        Swal.fire({
          icon:'error',
          title:'Lỗi',
          text:'Máy chủ chưa sẵn sàng để xử lý yêu cầu'
        })
      }else if(e.status==401){
        Swal.fire({
          icon:'error',
          title:'Lỗi',
          text:'Thông tin đăng nhập không đúng'
        })
      }else{
        Swal.fire({
          icon:'error',
          title:'Lỗi',
          text:e.statusText
        })
      }
    })
  }
}
