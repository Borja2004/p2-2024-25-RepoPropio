package org.DIS.practica2.vistas;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.DIS.practica2.modelos.Turismo;
import org.DIS.practica2.servicios.FrontService;
import org.springframework.cglib.core.internal.Function;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class GridTurismos extends VerticalLayout {
    private ArrayList<Turismo> listaTurismos;
    private final Grid<Turismo> grid = new Grid<>(Turismo.class, false);
    private final ListDataProvider<Turismo> dataProvider;
    private final Span resultadoSpan = new Span();
    private final Button botonNuevoTurismo = new Button("Añadir nuevo turismo");
    private final Map<Grid.Column<Turismo>, Predicate<Turismo>> filtrosActivos = new HashMap<>();

    private static final String COMUNIDAD_ORIGEN = "Comunidad Origen";
    private static final String COMUNIDAD_DESTINO = "Comunidad Destino";
    private static final String FECHA_INICIO = "Fecha Inicio";
    private static final String FECHA_FIN="Fecha Fin";

    public GridTurismos(FrontService service) {
        listaTurismos = Optional.ofNullable(service.getTurismos()).orElse(new ArrayList<>());
        dataProvider = new ListDataProvider<>(listaTurismos);
        grid.setDataProvider(dataProvider);

        // Columnas principales
        Grid.Column<Turismo> colComOrigen = grid.addColumn(t -> t.getOrigen().getComunidad())
                .setHeader(COMUNIDAD_ORIGEN).setSortable(true);
        Grid.Column<Turismo> colComDestino = grid.addColumn(t -> t.getDestino().getComunidad())
                .setHeader(COMUNIDAD_DESTINO).setSortable(true);
        Grid.Column<Turismo> colFechaInicio = grid.addColumn(t -> t.getPeriodo().getFecha_inicio())
                .setHeader(FECHA_INICIO).setSortable(true);
        Grid.Column<Turismo> colFechaFin = grid.addColumn(t -> t.getPeriodo().getFecha_fin())
                .setHeader(FECHA_FIN).setSortable(true);

        // Filtros
        HeaderRow filtroRow = grid.appendHeaderRow();
        añadirFiltroTexto(filtroRow, colComOrigen, t -> t.getOrigen().getComunidad(), COMUNIDAD_ORIGEN);
        añadirFiltroTexto(filtroRow, colComDestino, t -> t.getDestino().getComunidad(), COMUNIDAD_DESTINO);
        añadirFiltroFecha(filtroRow, colFechaInicio, t -> t.getPeriodo().getFecha_inicio(), FECHA_INICIO);
        añadirFiltroFecha(filtroRow, colFechaFin, t -> t.getPeriodo().getFecha_fin(), FECHA_FIN);

        // Grid
        grid.setWidthFull();
        grid.setMultiSort(true);
        grid.addClassName("grid-turismos");
        grid.setEmptyStateText("No se han encontrado resultados");

    }
    private void añadirFiltroTexto(HeaderRow row, Grid.Column<Turismo> col,
                                   Function<Turismo, String> valueProvider, String placeholder) {
        TextField filterField = new TextField();
        filterField.setPlaceholder(placeholder);
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(e -> {
            String valor = e.getValue().trim();
            if (valor.isEmpty()) {
                filtrosActivos.remove(col);
            } else {
                filtrosActivos.put(col, t -> {
                    String val = valueProvider.apply(t);
                    return val != null && val.toLowerCase().contains(valor.toLowerCase());
                });
            }
            aplicarFiltros();
        });
        row.getCell(col).setComponent(filterField);
        filterField.setSizeFull();
    }
    private void aplicarFiltros() {
        dataProvider.setFilter(t -> filtrosActivos.values().stream().allMatch(f -> f.test(t)));
        actualizarResultadoSpan();
    }
    private void actualizarResultadoSpan() {
        int total = dataProvider.size(new com.vaadin.flow.data.provider.Query<>());
        resultadoSpan.setText("Resultados encontrados: " + total);
    }

    public void refrescarGrid(ArrayList<Turismo> nuevaLista) {
        dataProvider.getItems().clear();
        dataProvider.getItems().addAll(nuevaLista);
        dataProvider.refreshAll();
        actualizarResultadoSpan();
    }
    private void añadirFiltroFecha(HeaderRow row, Grid.Column<Turismo> col,
                                   Function<Turismo, String> valueProvider, String placeholder) {
        DatePicker picker = new DatePicker();
        picker.setPlaceholder(placeholder);
        picker.setI18n(new DatePicker.DatePickerI18n()
                .setDateFormat("yyyy-MM-dd")
                .setFirstDayOfWeek(1)
                .setMonthNames(List.of("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"))
                .setWeekdays(List.of("Domingo", "Lunes", "Martes", "Miércoles", "Jueves",
                        "Viernes", "Sábado"))
                .setWeekdaysShort(List.of("Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"))
                .setToday("Hoy").setCancel("Cancelar"));

        picker.addValueChangeListener(e -> {
            LocalDate seleccion = e.getValue();
            if (seleccion == null) {
                filtrosActivos.remove(col);
            } else {
                filtrosActivos.put(col, t -> {
                    LocalDate val = LocalDate.parse(valueProvider.apply(t));
                    return val.equals(seleccion);
                });
            }
            aplicarFiltros();
        });

        row.getCell(col).setComponent(picker);
        picker.setSizeFull();
    }

}
