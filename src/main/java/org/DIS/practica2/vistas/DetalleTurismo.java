package org.DIS.practica2.vistas;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.DIS.practica2.servicios.FrontService;

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
}
