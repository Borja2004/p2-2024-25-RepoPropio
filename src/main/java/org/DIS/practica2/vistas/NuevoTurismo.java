package org.DIS.practica2.vistas;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.DIS.practica2.modelos.Destino;
import org.DIS.practica2.modelos.Origen;
import org.DIS.practica2.modelos.Periodo;
import org.DIS.practica2.modelos.Turismo;
import org.DIS.practica2.servicios.FrontService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

public class NuevoTurismo {
    TextField comunidadOrigen = new TextField("Comunidad Origen: ");
    TextField provinciaOrigen = new TextField("Provincia Origen: ");
    TextField comunidadDestino = new TextField("Comunidad Destino: ");
    TextField provinciaDestino = new TextField("Provincia Destino: ");
    DatePicker fechaInicio = new DatePicker("Fecha de Inicio: ");
    DatePicker fechaFin = new DatePicker("Fecha de Fin: ");
    IntegerField total = new IntegerField("Total: ");
    ConfirmDialog dialogoConfirmacion = new ConfirmDialog();
    FrontService service;

    public NuevoTurismo(FrontService service) {
        this.service = service;

        configurarDatePickers();
    }
    private void configurarDatePickers() {
        DatePicker.DatePickerI18n i18n = new DatePicker.DatePickerI18n()
                .setDateFormat("yyyy-MM-dd")
                .setFirstDayOfWeek(1)
                .setMonthNames(List.of("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"))
                .setWeekdays(List.of("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"))
                .setWeekdaysShort(List.of("Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"))
                .setToday("Hoy")
                .setCancel("Cancelar");

        fechaInicio.setI18n(i18n);
        fechaFin.setI18n(i18n);
    }
    public Dialog generateCreateDialog(Consumer<Void> onCloseCallback) {
        Dialog dialog = new Dialog();
        dialog.setWidth("450px");
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(false);
        dialog.setHeaderTitle("Nuevo Viaje");

        dialog.add(crearDialogLayout());

        Button cancelButton = new Button("Cancelar", e -> dialog.close());
        Button saveButton = new Button("Añadir", e -> {
            try {
                Turismo nuevoTurismo = construirTurismoDesdeFormulario();
                Turismo creado = service.crearTurismo(nuevoTurismo);

                if (creado != null) {
                    mostrarConfirmacion("Turismo creado", "El turismo ha sido creado correctamente.");
                } else {
                    mostrarConfirmacion("Error al crear el turismo", "Ha ocurrido un error. Inténtalo de nuevo.");
                }
                dialog.close();
                onCloseCallback.accept(null);
            } catch (Exception ex) {
                mostrarConfirmacion("Error", "Verifica los datos del formulario.");
            }
        });

        dialog.getFooter().add(cancelButton, saveButton);
        return dialog;
    }
    private Turismo construirTurismoDesdeFormulario() {
        Origen origen = new Origen(comunidadOrigen.getValue(), provinciaOrigen.getValue());
        Destino destino = new Destino(comunidadDestino.getValue(), provinciaDestino.getValue());

        String fechaInicioStr = fechaInicio.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String fechaFinStr = fechaFin.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
        Periodo periodo = new Periodo(fechaInicioStr, fechaFinStr);

        return new Turismo(origen, destino, periodo, total.getValue());
    }

    private void mostrarConfirmacion(String titulo, String mensaje) {
        dialogoConfirmacion.setHeader(titulo);
        dialogoConfirmacion.setText(mensaje);
        dialogoConfirmacion.setConfirmText("Aceptar");
        dialogoConfirmacion.open();
    }

    private VerticalLayout crearDialogLayout() {
        HorizontalLayout origenLayout = new HorizontalLayout(comunidadOrigen, provinciaOrigen);
        HorizontalLayout destinoLayout = new HorizontalLayout(comunidadDestino, provinciaDestino);
        HorizontalLayout fechasLayout = new HorizontalLayout(fechaInicio, fechaFin);

        VerticalLayout layout = new VerticalLayout(origenLayout, destinoLayout, fechasLayout, total);
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        layout.getStyle().set("width", "18rem").set("max-width", "100%");

        return layout;
    }
}
