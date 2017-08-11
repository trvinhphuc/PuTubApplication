import { Component, OnInit, ViewChild } from '@angular/core';
import { DxFormComponent } from 'devextreme-angular';
import { Http, Response, Headers, RequestOptions, RequestOptionsArgs } from '@angular/http';
import { Router } from '@angular/router';
import { UserService } from './../services/user.service';
import notify from 'devextreme/ui/notify';
@Component({
  selector: 'app-logup',
  templateUrl: './logup.component.html',
  styleUrls: ['./logup.component.css'],
  providers: [UserService],
})
export class LogupComponent implements OnInit {
  @ViewChild(DxFormComponent) form: DxFormComponent;

  user: any;
  password = "";
  passwordOptions: any = {
    mode: "password",
    value: this.password
  };
  emailPattern: any = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
  constructor(private logupService: UserService,
    private route: Router
  ) {
    this.user = {
      username: '',
      password: '',
      email: '',
      channelname: '',
      date: '',
      ava: ''
    };

  }
  passwordComparison = () => {
    return this.form.instance.option("formData").password;
  }
  checkComparison() {
    return true;
  }
  onFormSubmit = function (e) {
    if(this.user.ava != '') this.user.ava = this.user.ava[0].name;
    this.logupService.logup(this.user)
      .subscribe(data => {
        //console.log(data);
        if (data == true) {
          this.route.navigate(['home']);
          notify({
            message: "Chúc mừng bạn đã đăng ký thành công!!! \n Chào mừng bạn đến với PuTub",
            position: {
              my: "center",
              at: "center"
            }
          }, "success", 2000);
          setTimeout(() => {
            window.location.reload();
          }, 2500);
        }
        else alert("Can not register");
      });

  }
  register() {
    this.onFormSubmit;
  }
  ngOnInit() {
  }

}