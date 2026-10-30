import { Component, inject, output, signal } from '@angular/core';
import { CategoriesService } from '../../../services/categories.service';
import { form, required, FormField } from '@angular/forms/signals';
import { CatalogService } from '../../../services/catalog.service';

@Component({
  selector: 'app-create-house',
  imports: [FormField],
  templateUrl: './create-house.component.html',
  styleUrl: './create-house.component.css',
})
export class CreateHouseComponent {
  close = output<void>();
  categoriesService = inject(CategoriesService);
  catalogService = inject(CatalogService);

  formData = signal({
    name: '',
    description: '',
    imgUrl: '',
    price: 0,
    categoryId: '',
  });

  houseForm = form(this.formData, (data) => {
    required(data.name, {
      message: 'El nombre de la casa es obligatorio.'
    });
    required(data.description, {
      message: 'La descripción de la casa es obligatoria.'
    });
    required(data.imgUrl, {
      message: 'La URL de la imagen es obligatoria.'
    });
    required(data.price, {
      message: 'El precio de la casa es obligatorio.'
    });
    required(data.categoryId, {
      message: 'La categoría de la casa es obligatoria.'
    });
  });

  closeModal() {
    this.close.emit();
  }

  onSubmit(event: SubmitEvent) {
    if(event) event.preventDefault();

    if(this.houseForm().valid()){
      const formValue = this.houseForm().value();
      const houseData = {
        name: formValue.name,
        description: formValue.description,
        imgUrl: formValue.imgUrl,
        price: formValue.price,
        categoryId: formValue.categoryId
      };
      this.catalogService.createHouse(houseData);
      this.houseForm().reset();
      this.closeModal();
    }
  }
}
