import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { AppServiceService } from 'src/app/service/app-service.service';
import Swal from 'sweetalert2';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private appService: AppServiceService,private router:Router,private cookieService:CookieService) {
    this.userif = {} as User
  }


  public name:string='dasdas';
  public userif:User;
  public u:any =[];

  ngOnInit(): void {
    this.loadUserif();
  }

  public async loadUserif() {
    await this.appService.getUserif().subscribe(data => {
      this.userif = data as User;
      console.log(this.userif)
      this.u = data
    }, err => {
      console.log(err)

      if (err.status == 401) {
        Swal.fire({
          icon: 'error',
          title: 'Lỗi xác thực đăng nhập',
          text: err.message
        }).then(  
          ()=>{this.router.navigate(['']);}
        )
      }
    })
  }

  public logout(){
    this.cookieService.delete('auth');
    document.location.href='';  
  }
}
