package org.DIS.practica2.vistas;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.DIS.practica2.servicios.FrontService;

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
}
