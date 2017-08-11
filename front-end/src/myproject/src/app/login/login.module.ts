import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
//import { BrowserModule } from '@angular/platform-browser';
import { HttpModule }    from '@angular/http';
import { LoginComponent } from './login.component';
import { LoginRoutingModule } from './login-routing.module';

/*
creator: Truong Vinh Phuc
function: login page, only for new user
date:11/08/2017
note:
*/
@NgModule({
    imports:[
        CommonModule,
        FormsModule,
        //BrowserModule,
        HttpModule,
        LoginRoutingModule,
       
    ],
    
    declarations:[
      LoginComponent
        
    ]

})
export class LoginModule {}