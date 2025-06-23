package org.DIS.practica2;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;
import org.DIS.practica2.modelos.Turismo;
import org.DIS.practica2.servicios.FrontService;

import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {
    /**
     * Vista principal de la aplicación Vaadin.
     * Contiene dos pestañas:
     * - Datos generales: lista completa de turismos.
     * - Datos agrupados: turismos filtrados por comunidad de destino.
     */
    public MainView(FrontService service) {
        // Componente de pestañas
        TabSheet tabs = new TabSheet();

        // Primera pestaña: Datos generales
        VerticalLayout datosGeneralesLayout = new VerticalLayout();
        GridTurismos turismosGrid = new GridTurismos(service);
        turismosGrid.setSizeFull();
        datosGeneralesLayout.setSizeFull();
        datosGeneralesLayout.add(turismosGrid);

        // Segunda pestaña: Agrupación por comunidad
        GridTurismos turismosAgrupadosGrid = new GridTurismos(service);
        turismosAgrupadosGrid.setSizeFull();

        List<String> opcionesComunidades = service.getComunidades();
        ComboBox<String> selector = new ComboBox<>("Selecciona una Comunidad de Destino");
        selector.setItems(opcionesComunidades);

        selector.addValueChangeListener(event -> {
            String seleccion = event.getValue();
            if (seleccion != null) {
                ArrayList<Turismo> filtrado = service.getTurismoByComunidad(seleccion);
                turismosAgrupadosGrid.refrescarGrid(filtrado);
            }
        });
/**
 * Vista principal de la aplicación Vaadin.
 * Contiene dos pestañas:
 * - Datos generales: lista completa de turismos.
 * - Datos agrupados: turismos filtrados por comunidad de destino.
 */
    public MainView(FrontService service) {
            // Componente de pestañas
            TabSheet tabs = new TabSheet();

            // Primera pestaña: Datos generales
            VerticalLayout datosGeneralesLayout = new VerticalLayout();
            GridTurismos turismosGrid = new GridTurismos(service);
            turismosGrid.setSizeFull();
            datosGeneralesLayout.setSizeFull();
            datosGeneralesLayout.add(turismosGrid);

            // Segunda pestaña: Agrupación por comunidad
            GridTurismos turismosAgrupadosGrid = new GridTurismos(service);
            turismosAgrupadosGrid.setSizeFull();

            List<String> opcionesComunidades = service.getComunidades();
            ComboBox<String> selector = new ComboBox<>("Selecciona una Comunidad de Destino");
            selector.setItems(opcionesComunidades);

            selector.addValueChangeListener(event -> {
                String seleccion = event.getValue();
                if (seleccion != null) {
                    ArrayList<Turismo> filtrado = service.getTurismoByComunidad(seleccion);
                    turismosAgrupadosGrid.refrescarGrid(filtrado);
                }
            });

        }
    }
}
