import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HeaderComponent} from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ContentComponent } from './components/content/content.component';
import {RouterModule} from "@angular/router";
import {ContainerComponent} from "src/app/layouts/components/container/container.component";



@NgModule({
  declarations: [
    ContainerComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    ContentComponent,
  ],
  exports: [
    ContainerComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    ContentComponent,
  ]
  ,
    imports: [
      CommonModule,
      RouterModule
    ]
})
export class LayoutsModule { }
