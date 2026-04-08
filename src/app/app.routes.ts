import { Routes } from '@angular/router';
import { Home } from './home/home';
import { NotFound } from './not-found/not-found';
import { Contact } from './contact/contact';
import { FindUs } from './find-us/find-us';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: Home },
    { path: 'contact', component: Contact },
    { path: 'find-us', component: FindUs },
    { path: '**', component: NotFound }
];
