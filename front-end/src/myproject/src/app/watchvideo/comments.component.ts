import { Component, OnInit, Input } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { UserService } from "../services/user.service";
import { VideoService } from '../services/video_service.service';
import { CommentService } from "../services/comments.service";

import { FormControl } from '@angular/forms';

import notify from 'devextreme/ui/notify';
//import 'rxjs/add/operator/switchMap';
//import '../../test.mp4';
import { Router, ActivatedRoute, Params } from '@angular/router';


@Component({
    selector: 'comments',
    templateUrl: './comments.component.html',
    styleUrls: ['./comments.component.css'],
    providers: [UserService, CommentService],

})

export class CommentsComponent implements OnInit {

    @Input() id_video: any;
    video: any;
    comments: any;
    comment: any;
    imageData: any;
    user: any;
    current_user: any;
    show_list: any[];
    count: number;
    visible: boolean;
    check: boolean;
    items: Observable<Array<string>>;
    constructor(
        private route: ActivatedRoute,
        private commentService: CommentService,
        private service: UserService,
        private router: Router,
        private videoService:VideoService,
    ) {
        this.user = {
            id: ''
        };
        this.comment = {
            id_user: '',
            id_video: '',
            content: '',
            date: ''
        };
        this.check = true;
        this.current_user = this.service.getCookie('userLogin');//id user
        this.count = 10;
        this.visible = false;
    }
    getComments() {
        
        this.commentService.getComments(this.id_video)
            .subscribe(data => {
                this.comments = data;
                this.show_list = this.comments.slice(0, this.count);
                if (this.comments.length > 10) this.visible = true;
            })
        this.videoService.getVid(this.id_video)
            .subscribe(data =>{
                this.video = data;
            })

    }
    setComment() {

        this.comment.id_user = this.current_user;
        if (this.comment.id_user != '') {
            this.comment.id_video = this.id_video;
            this.commentService.setComment(this.comment)
                .subscribe(data => { this.getComments(); this.comment.content = null;this.checkEmp(); })
            this.check = true;
            window.scrollTo(0, document.body.scrollHeight);
        }
        else {
            notify({
                message: "Đăng nhập cái rồi bình luận nhé ^^",
                position: {
                    my: "center",
                    at: "center"
                }
            }, "warning", 3000);
            this.router.navigate(["login"])
        }

        //console.log(this.comment.content);
    }
    delComment(cmt: any) {
        this.comment.id_user = this.current_user;
        this.comment.id_video = this.id_video;
        this.comment.date = cmt.date;
        this.commentService.delComment(this.comment)
            .subscribe(data => { this.getComments() })
    }
    more() {
        this.count = this.count + 4;
        this.show_list = this.comments.slice(1, this.count);
    }
    checkEmp(){
        if(this.comment.content != null){
            this.check = false;
        }
        if(this.comment.content == ""){
            this.check = true;
        }
    }
    ngOnInit() {
        this.getComments();
    }
}