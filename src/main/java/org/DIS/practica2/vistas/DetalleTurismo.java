package org.DIS.practica2.vistas;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.DIS.practica2.modelos.Turismo;
import org.DIS.practica2.servicios.FrontService;

import java.util.List;
import java.util.function.Consumer;

public class DetalleTurismo {
    private final TextField comunidadOrigen = new TextField("Comunidad Origen:");
    private final TextField provinciaOrigen = new TextField("Provincia Origen:");
    private final TextField comunidadDestino = new TextField("Comunidad Destino:");
    private final TextField provinciaDestino = new TextField("Provincia Destino:");
    private final DatePicker fechaInicio = new DatePicker("Fecha de Inicio:");
    private final DatePicker fechaFin = new DatePicker("Fecha de Fin:");
    private final IntegerField total = new IntegerField("Total:");

    private final ConfirmDialog dialogoConfirmacion = new ConfirmDialog();
    private final FrontService service;

    public DetalleTurismo(FrontService service) {
        this.service = service;

        // Configura los DatePicker con localización en español
        setDatePickerLocale(fechaInicio);
        setDatePickerLocale(fechaFin);
    }
    public Dialog generateEditDialog(Turismo turismo, Consumer<Void> onCloseCallback) {
        Dialog dialog = new Dialog();
        dialog.setWidth("450px");
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(false);
        dialog.setHeaderTitle("Editar Turismo");

        // Rellenar los campos con los datos del objeto turismo
        comunidadOrigen.setValue(turismo.getOrigen().getComunidad());
        provinciaOrigen.setValue(turismo.getOrigen().getProvincia());
        comunidadDestino.setValue(turismo.getDestino().getComunidad());
        provinciaDestino.setValue(turismo.getDestino().getProvincia());
        fechaInicio.setValue(turismo.getPeriodo().getLocalDateInicio());
        fechaFin.setValue(turismo.getPeriodo().getLocalDateFin());
        total.setValue(turismo.getTotal());

        dialog.add(crearLayoutFormulario());

        // Botón para cancelar la edición
        Button cancelar = new Button("Cancelar", e -> dialog.close());

        // Botón para guardar los cambios
        Button guardar = new Button("Guardar", e -> {
            Turismo actualizado = new Turismo(
                    new Origen(comunidadOrigen.getValue(), provinciaOrigen.getValue()),
                    new Destino(comunidadDestino.getValue(), provinciaDestino.getValue()),
                    new Periodo(fechaInicio.getValue().toString(), fechaFin.getValue().toString()),
                    total.getValue()
            );

            service.editarTurismo(turismo.getId(), actualizado);

            dialogoConfirmacion.setHeader("Actualización completada");
            dialogoConfirmacion.setText("Los datos del turismo han sido actualizados.");
            dialogoConfirmacion.setConfirmText("Aceptar");
            dialogoConfirmacion.open();

            dialog.close();
            onCloseCallback.accept(null);
        });

        dialog.getFooter().add(cancelar, guardar);
        return dialog;
    }

    private void setDatePickerLocale(DatePicker picker) {
        picker.setI18n(new DatePicker.DatePickerI18n()
                .setDateFormat("yyyy-MM-dd")
                .setFirstDayOfWeek(1)
                .setMonthNames(List.of("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"))
                .setWeekdays(List.of("Domingo", "Lunes", "Martes", "Miércoles", "Jueves",
                        "Viernes", "Sábado"))
                .setWeekdaysShort(List.of("Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"))
                .setToday("Hoy")
                .setCancel("Cancelar"));
    }
}
