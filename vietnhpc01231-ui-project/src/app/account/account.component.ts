import { Component, OnInit } from '@angular/core';
import {NgxPaginationModule} from 'ngx-pagination';
import {
  FormGroup,
  FormControl,
  Validators,
  AbstractControl,
  FormBuilder,
} from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AppServiceService } from '../service/app-service.service';
@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  constructor(private appService: AppServiceService,private router:Router) { }

  public form = new FormGroup({
    fullname: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required,Validators.email]),
    phone: new FormControl('', [Validators.required, Validators.pattern('(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b')]),
    gender: new FormControl('true'),
    role: new FormControl('user'),
  })
  public term:any ="";
  public  p: number = 1;
  public useredit:string="";
  public editing: any = false;
  public checkall: any = false;
  public listUser: any = [];
  ngOnInit(): void {
    this.loadListUser();
  }
  public createUser() {
    if (!this.form.valid) {
      this.checkall = true;
    } else {
      this.appService.createUser(this.form.value).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Thêm thành công'
        })
        this.loadListUser();
        this.resetForm();
      }, err => {
        if (err.status == 400) {
          Swal.fire({
            icon: 'error',
            title: 'Lỗi',
            text: "Tài khoản đã tồn tại"
          })
        } else if (err.status == 403) {
          Swal.fire({
            icon: 'error',
            title: 'Lỗi',
            text: "Không có quyền!"
          })
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Lỗi chưa xác định',
            text: err.message
          })
        }
      })
    }
  }
  public updateUser() {
    if (!this.form.valid) {
      this.checkall = true;
    } else {
      this.appService.updateUser(this.form.value).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Cập nhật thông tin thành công'
        })
        this.loadListUser();
        this.resetForm();
      }, err => {
        console.log(err)
        if (err.status == 400) {
          Swal.fire({
            icon: 'error',
            title: 'Lỗi',
            text: "Không tìm thấy tên tài khoản"
          })
        } else if (err.status == 403) {
          Swal.fire({
            icon: 'error',
            title: 'Lỗi',
            text: err.error.message
          })
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Lỗi chưa xác định',
            text: err.message
          })
        }
      })
    }
  }
  public loadListUser() {
    this.appService.loadListUser().subscribe(data => {
      this.listUser = data;
      console.log(this.listUser)
    },err=>{
     console.log(err)
     if(err.status==403){
      Swal.fire({
        icon: 'error',
        title: 'Không có quyền truy cập',
        text: err.message
      }).then(()=>{
        this.router.navigate(['']);
      })
     }
    })
  }

  public editUser(username: string) {
    this.appService.getUserByUsername(username).subscribe(data => {
      console.log(data)
      for (const controllname in this.form.controls) {

        this.form.controls[controllname].setValue(data[controllname])

      }
      for (const e in data.roles) {
        if (data.roles[e].role.id == 'PM') {
          this.form.controls['role'].setValue('pm')
          break;
        } else if (data.roles[e].role.id == 'AD') {
          this.form.controls['role'].setValue('admin')
          break;
        } else {
          this.form.controls['role'].setValue('user')
        }
      }
      this.form.controls['gender'].setValue(data.gender+'')
      this.editing=true;
      this.useredit=data.username;
    })
  }
  
  public resetForm(){
    this.useredit="";
    this.checkall=false;
    this.editing=false;
    this.form.reset();
    this.form.controls['role'].setValue('user');
    this.form.controls['gender'].setValue('true');
  }
  public deleteUserEdit(){
    if(this.editing && this.useredit!=""){
      this.appService.deleteUser(this.useredit).subscribe(data=>{
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Xóa thành công'
        })
        this.loadListUser();
        this.resetForm();
      },err=>{
        console.log(err)
        Swal.fire({
          icon: 'error',
          title: 'Lỗi',
          text: err.error.message
        })
      })
    }
  }

  
  public deleteUser(username:string){
      this.appService.deleteUser(username).subscribe(data=>{
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Xóa thành công'
        })
        this.loadListUser();
        this.resetForm();
      },err=>{
        console.log(err)
        Swal.fire({
          icon: 'error',
          title: 'Lỗi',
          text: err.error.message
        })
      })
  }
}
