package org.DIS.practica2.vistas;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.DIS.practica2.modelos.Turismo;
import org.DIS.practica2.servicios.FrontService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    }
}
