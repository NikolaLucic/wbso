package com.bol.wbso;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class WeekView extends CustomComponent implements View {

    public static final String NAME = "week";

    Label text = new Label();
    Table weekTable = new Table();


	Button logout = new Button("Logout", new Button.ClickListener() {
		
		@Override
		public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
			// "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(LoginView.NAME);
			
		}
	});

    public WeekView() {
        weekTable.addContainerProperty("Activity", ComboBox.class, "");
        weekTable.addContainerProperty("Monday", Integer.class, "");
        weekTable.addContainerProperty("Tuesday", Integer.class, "");
        weekTable.addContainerProperty("Wendsday", Integer.class, "");
        weekTable.addContainerProperty("Thursday", Integer.class, "");
        weekTable.addContainerProperty("Friday", Integer.class, "");
        weekTable.addContainerProperty("Saturday?", Integer.class, "");
        weekTable.addContainerProperty("Sunday....really?", Integer.class, "");
        
    	HorizontalLayout mainLayout = new HorizontalLayout(weekTable, logout);
    	mainLayout.setSizeFull();
    	mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    	mainLayout.setSpacing(true);
    	mainLayout.setMargin(new MarginInfo(true, true, true, false));
    	setCompositionRoot(mainLayout);







    }

    @Override
    public void enter(ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));

        // And show the username
        text.setValue("Hello " + username);
    }
}