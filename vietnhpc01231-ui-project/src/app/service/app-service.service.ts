import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import {
  HttpHeaders,
  HttpClient,HttpRequest,
  HttpErrorResponse,
} from '@angular/common/http';
import { User } from '../model/user.model';
@Injectable({
  providedIn: 'root'
})
export class AppServiceService {
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+this.cookieService.get('auth')
    }),
  };
  constructor(private httpClient: HttpClient,private cookieService:CookieService ) { }

  private REST_API_SERVER='http://localhost:8080/api/';

  public login(data:any){
    return this.httpClient.post<any>(this.REST_API_SERVER+'login',data);
  }
  public getUserif(){
    return this.httpClient.get<User>(this.REST_API_SERVER+'user',this.httpOptions);
  }

  public createUser(data:any){
    return this.httpClient.post<any>(this.REST_API_SERVER+'create/'+data.role,data,this.httpOptions);
  }

  public updateUser(data:any){
    return this.httpClient.put<any>(this.REST_API_SERVER+'update/user?role='+data.role,data,this.httpOptions);
  }

  public loadListUser(){
    return this.httpClient.get<any>(this.REST_API_SERVER+'list/user',this.httpOptions);
  }

  public getUserByUsername(username:string){
    return this.httpClient.get<any>(this.REST_API_SERVER+'user/'+username,this.httpOptions);
  }

  public deleteUser(username:string){
    return this.httpClient.delete<any>(this.REST_API_SERVER+'delete/'+username,this.httpOptions)
  }

  ////////////////////////// category /////////////////////////////////

  public createParentCategory(data:any){
    return this.httpClient.post<any>(this.REST_API_SERVER+'create/parentcategory',data,this.httpOptions)
  }
  public loadParentCategory(){
    return this.httpClient.get<any>(this.REST_API_SERVER+'getall/parentcategory',this.httpOptions)
  }

  public updateParentCategory(data:any){
    return this.httpClient.put<any>(this.REST_API_SERVER+'update/parentcategory',data,this.httpOptions)
  }

  public deleteParentCategory(id:string){
    return this.httpClient.delete<any>(this.REST_API_SERVER+'delete/parentcategory/'+id,this.httpOptions)
  }

  public LoadParentCategoryEnableTrue(){
    return this.httpClient.get<any>(this.REST_API_SERVER+'get/parentcategoryenable',this.httpOptions)
  }

  public createCategory(data:any){
    return this.httpClient.post<any>(this.REST_API_SERVER+'create/category?id='+data.parents,data,this.httpOptions)
  }
  public getAllCategory(){
    return this.httpClient.get<any>(this.REST_API_SERVER+'getall/category',this.httpOptions)
  }
  public updateCategory(data:any){
    return this.httpClient.put<any>(this.REST_API_SERVER+'update/category?id='+data.parents,data,this.httpOptions)
  }
  public deleteCategory(id:string){
    return this.httpClient.delete<any>(this.REST_API_SERVER+'delete/category/'+id,this.httpOptions)
  }

  public LoadProduct(){
    return this.httpClient.get<any>(this.REST_API_SERVER+'get/product',this.httpOptions)
  }
  
  public createProduct(data:any){
    return this.httpClient.post<any>(this.REST_API_SERVER+'create/product?id='+data.idcategory,data,this.httpOptions)
  }
  public async upload(data:any){
    const formData: FormData = new FormData();
    formData.append('file', data[0]);
    return await this.httpClient.post<any>(this.REST_API_SERVER+'upload/images',formData,{ reportProgress: true
     }).toPromise() ; 
  }

  public deleteProduct(id:string){
    return this.httpClient.delete<any>(this.REST_API_SERVER+'delete/product/'+id,this.httpOptions)
  }
  public updateProduct(data:any){
    return this.httpClient.put<any>(this.REST_API_SERVER+'update/product?id='+data.idcategory,data,this.httpOptions)
  }
}
