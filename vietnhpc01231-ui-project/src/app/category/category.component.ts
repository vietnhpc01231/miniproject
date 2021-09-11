import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  AbstractControl,
  FormBuilder,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { AppServiceService } from '../service/app-service.service';
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  constructor(private appService: AppServiceService) { }

  public parent = new FormGroup({
    id: new FormControl(''),
    name: new FormControl('', [
      Validators.required
    ]),
    enable: new FormControl('true'),
  });
  public category = new FormGroup({
    id: new FormControl('true'),
    name: new FormControl('', [
      Validators.required,
    ]),
    parents: new FormControl('', Validators.required),
    enable: new FormControl('true'),
  });
  public term2:any ="";
  public  p2: number = 1;
  public term:any ="";
  public  p: number = 1;
  public listCategory:any = [];
  public listParentEnableTrue: any =[];
  public checkFormCategory: any = false;
  public checkFormParent: any = false;
  public editingParent: any = false;
  public editingCategory:any = false;
  public listParentCategory: any = [];
  ngOnInit(): void {
    this.loadParentCategory();
  }

  public createParentCategory() {
    if (!this.parent.valid) {
      this.checkFormParent = true;
    } else {
      console.log(this.parent.value);
      this.appService.createParentCategory(this.parent.value).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Thêm thành công'
        })
        this.loadParentCategory();
        this.resetFormParentCategory();
      }, err => {
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: err.message
        })
      })
    }
  }

  public loadParentCategory() {
    this.appService.loadParentCategory().subscribe(data => {
      this.listParentCategory = data;
      console.log(data)
    })
  }

  public editParentCategory(item: any) {
    for (const controlname in this.parent.controls) {
      for (const namedata in item) {
        if (controlname == namedata) {
          this.parent.controls[controlname].setValue(item[namedata]);
        }
      }
    }
    this.parent.controls['enable'].setValue(item.enable ? 'true' : 'false');
    this.editingParent = true;
  }
  public resetFormParentCategory() {
    this.checkFormParent=false;
    this.editingParent = false;
    this.parent.reset();
    this.parent.controls['enable'].setValue('true');
  }

  public updateParentCategory() {
    if (this.editingParent) {
      this.appService.updateParentCategory(this.parent.value).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Cập nhật thành công'
        })
        this.loadParentCategory();
        this.resetFormParentCategory();
      }, err => {
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: err.message
        })
      })
    }
  }
  public deleteParentCategory() {
    if (this.editingParent) {
      this.appService.deleteParentCategory(this.parent.controls['id'].value).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Xóa thành công'
        })
        this.loadParentCategory();
        this.resetFormParentCategory();
      }, err => {
        if(err.status==403){
          Swal.fire({
            icon: 'error',
            title: 'Thông báo',
            text: "Không thể xóa danh mục đang có danh mục con"
          })
         }else{
          Swal.fire({
            icon: 'error',
            title: 'Thông báo',
            text: err.message
          })
         }
      })
    }
  }

  public deleteParentCategoryrow(id: string) {
    this.appService.deleteParentCategory(id).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Thông báo',
        text: 'Xóa thành công'
      })
      this.loadParentCategory();
    }, err => {
      if(err.status==403){
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: "Không thể xóa danh mục đang có danh mục con"
        })
       }else{
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: err.message
        })
       }
    })
  }/////////////////////////////// page category////////////////////////
  public LoadParentCategoryEnableTrue(){
    this.appService.LoadParentCategoryEnableTrue().subscribe(data=>{
     this.listParentEnableTrue=data;
    })
  }

  public loadCategory(){
    this.appService.getAllCategory().subscribe(data=>{
      this.listCategory=data;
      console.log(data)
    })
  }
  public createCategory(){
    this.checkFormCategory=true;
    if(this.category.valid){
      console.log(this.category.value)
      this.appService.createCategory(this.category.value).subscribe(data=>{
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Thêm thành công'
        })
       this.loadCategory();
      },err=>{
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: err.message
        })
      })
    }
  }
  public editCategory(item: any) {
    for (const controlname in this.category.controls) {
      for (const namedata in item) {
        if (controlname == namedata) {
          this.category.controls[controlname].setValue(item[namedata]);
        }
      }
    }

    this.category.controls['enable'].setValue(item.enable ? 'true' : 'false');
    this.category.controls['parents'].setValue(item.parent.id);
    this.editingCategory = true;
  }
  public resetFormCategory() {
    this.checkFormCategory=false;
    this.editingCategory = false;
    this.category.reset();
    this.category.controls['enable'].setValue('true');
  }

  public updateCategory() {
    if (this.editingCategory) {
      console.log(this.category.value)
      this.appService.updateCategory(this.category.value).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Cập nhật thành công'
        })
        this.loadCategory();
        this.resetFormCategory();
      }, err => {
       
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: err.message
        })
      })
    }
  }

  public deleteCategory() {
    if (this.editingCategory) {
      this.appService.deleteCategory(this.category.controls['id'].value).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Thông báo',
          text: 'Xóa thành công'
        })
        this.loadCategory();
        this.resetFormCategory();
      }, err => {
        if(err.status==403){
          Swal.fire({
            icon: 'error',
            title: 'Thông báo',
            text: "Không thể xóa danh mục đang có sản phẩm"
          })
         }else{
          Swal.fire({
            icon: 'error',
            title: 'Thông báo',
            text: err.message
          })
         }
      })
    }
  }

  public deleteCategoryrow(id: string) {
    this.appService.deleteCategory(id).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Thông báo',
        text: 'Xóa thành công'
      })
      this.loadCategory();
    }, err => {
      if(err.status==403){
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: "Không thể xóa danh mục đang có sản phẩm"
        })
       }else{
        Swal.fire({
          icon: 'error',
          title: 'Thông báo',
          text: err.message
        })
       }
    })
  }
}
