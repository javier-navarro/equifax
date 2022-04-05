import { NgModule } from '@angular/core';

import {
  MatCardModule,
  MatInputModule,
  MatButtonModule,
  MatTableModule,
  MatSelectModule,
  MatFormFieldModule
} from '@angular/material';

const modules = [
  MatCardModule,
  MatInputModule,
  MatButtonModule,
  MatTableModule,
  MatSelectModule,
  MatFormFieldModule
];

@NgModule({
  imports: modules,
  exports: modules,
})
export class MaterialModule {}
