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
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private appService:AppServiceService) { }
  public form = new FormGroup({
    id: new FormControl('', []),
    name: new FormControl('', [
      Validators.required
    ]),
    enable: new FormControl('true'),
    price : new FormControl(0,[Validators.required]),
    quantity : new FormControl(0,[Validators.required]),
    idcategory : new FormControl('',[Validators.required]),
    image: new FormControl('')
  })
  public checkForm : any = false;
  public term:any ="";
  public  p: number = 1;
  public uriImg:any = "http://localhost:8080/api/image/16f320c5.png";
  public listCategory:any =[];
  public listProduct:any =[];
  public imageEditName:any="";
  public editting:any=false;
  ngOnInit(): void {
    this.loadCategory();
    this.loadProduct();
  }
  public loadCategory(){
    this.appService.getAllCategory().subscribe(data=>{
      this.listCategory=data;
    })
  }

 public  uploadFile(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    let fileList: FileList | null = element.files;
      this.appService.upload(fileList).then(data=>{
      console.log(data)
      this.uriImg="http://localhost:8080/api/image/"+data.imagename;
      this.imageEditName=data.imagename;
    },err=>{
      console.log(err)
    })
  }

  public loadProduct(){
    this.appService.LoadProduct().subscribe(data=>{
      this.listProduct=data;
      console.log(data)
    })
  }

  public createProduct(){
    this.checkForm=true;
   if(this.form.valid){
    console.log(this.form.value)
    this.form.controls['image'].setValue(this.imageEditName);
    this.appService.createProduct(this.form.value).subscribe(data=>{
      Swal.fire({
        icon:'success',
        title:'Thông báo',
        text:'Thêm thành công'
      })
      this.loadProduct();
      this.resetForm();
    },err=>{
      console.log(err)
    })
   }
  }

  public edit(item:any){
    console.log(item)
    for(const controlname in this.form.controls){
      for(const itemname in item){
        if(controlname==itemname){
          this.form.controls[controlname].setValue(item[itemname]);
        }
      }
     this.imageEditName=item['image'];
    }

    this.form.controls['enable'].setValue(item['enable']?'true':'false');
    this.uriImg="http://localhost:8080/api/image/"+item.image;
    this.form.controls['idcategory'].setValue(item.category.id);
  
    this.editting = true;
  }
  public resetForm(){
    this.form.reset();
    this.form.controls['enable'].setValue('true')
    this.checkForm=false;
    this.editting=false;
    this.uriImg="";
    this.form.controls['price'].setValue(0)
    this.form.controls['quantity'].setValue(0)
    this.imageEditName="";
  }

  public deleteProduct(){
    if(this.editting){
      this.appService.deleteProduct(this.form.controls['id'].value).subscribe(data=>{
        Swal.fire({
          icon:'success',
          title:'Thông báo',
          text:'Xóa thành công'
        })
        this.loadProduct();
        this.resetForm();
      },err=>{
        Swal.fire({
          icon:'error',
          title:'Thông báo',
          text:err.message
        })
      })
    }
  }

  public deleteProductbyID(id:any){
      this.appService.deleteProduct(id).subscribe(data=>{
        Swal.fire({
          icon:'success',
          title:'Thông báo',
          text:'Xóa thành công'
        })
        this.loadProduct();
        this.resetForm();
      },err=>{
        Swal.fire({
          icon:'error',
          title:'Thông báo',
          text:err.message
        })
      })
  }
  public updateProduct(){
    console.log(this.form.value)
    this.form.controls['image'].setValue(this.imageEditName);
    this.appService.updateProduct(this.form.value).subscribe(data=>{
      this.loadProduct();
      this.resetForm();
    },err=>{
      console.log(err)
    })
  }
}
