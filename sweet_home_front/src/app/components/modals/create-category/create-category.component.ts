import { Component, inject, output, signal } from '@angular/core';
import { CategoriesService } from '../../../services/categories.service';
import { form, required, FormField } from '@angular/forms/signals';

@Component({
  selector: 'app-create-category',
  imports: [FormField],
  templateUrl: './create-category.component.html',
  styleUrl: './create-category.component.css',
})
export class CreateCategoryComponent {
  close = output<void>();
  categoriesService = inject(CategoriesService);

  public formData = signal({
    name: '',
    description: '',
  })

  public categoryForm = form(this.formData, (data) => {
    required(data.name, {
      message: 'El nombre de la categoría es obligatorio.'
    });
    required(data.description, {
      message: 'La descripción de la categoría es obligatoria.'
    });
  }
  );

  closeModal() {
    this.close.emit();
  }

  onSubmit(event: SubmitEvent) {
    if(event) event.preventDefault();

    if(this.categoryForm().valid()){
      const formValue = this.categoryForm().value();
      const categoryData = {
        name: formValue.name,
        description: formValue.description
      };
      this.categoriesService.createCategory(categoryData);
      this.categoryForm().reset();
      this.closeModal();
    }
  }
}
