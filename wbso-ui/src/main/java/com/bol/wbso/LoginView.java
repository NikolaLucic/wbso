package com.bol.wbso;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class LoginView extends CustomComponent implements View, Button.ClickListener {

	static final String NAME = "login";

	private final TextField username;
	private final PasswordField password;
	private final Button loginButton;

	public LoginView() {
		super();
		setSizeFull();

		username = new TextField("Username:");
		username.setWidth("300px");
		username.setRequired(true);
		username.setInputPrompt("Your username (eg. joe@email.com)");
		username.addValidator(new EmailValidator("Username must be an email address"));
		username.setInvalidAllowed(false);

		password = new PasswordField("Password:");
		password.setWidth("300px");
		password.addValidator(new PasswordValidator("Provided password is not valid."));
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");

		loginButton = new Button("Login", this);

		VerticalLayout fieldsLayout = new VerticalLayout(username, password, loginButton);
		fieldsLayout.setCaption("Please login with your domain credentials.");
		fieldsLayout.setSpacing(true);
		fieldsLayout.setMargin(new MarginInfo(true, true, true, false));
		fieldsLayout.setSizeUndefined();

		VerticalLayout viewLayout = new VerticalLayout(fieldsLayout);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(fieldsLayout, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);

	}

	private class PasswordValidator extends AbstractValidator<String> {

		public PasswordValidator(String errorMessage) {
			super(errorMessage);
		}

		@Override
		protected boolean isValidValue(String value) {
			if (value != null && (value.length() < 6 || !value.matches(".*\\d.*"))) {
				return false;
			}
			return true;
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}

	}

	@Override
	public void enter(ViewChangeEvent event) {
		username.focus();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (!username.isValid() || !password.isValid()) {
			return;
		}

		String username = this.username.getValue();
		String password = this.password.getValue();

		boolean isValid = username.equals("test@test.com") && password.equals("passw0rd");

		if (isValid) {
			getSession().setAttribute("user", username);

			getUI().getNavigator().navigateTo(WeekView.NAME);

		} else {

			// Wrong password clear the password field and refocuses it
			this.password.setValue(null);
			this.password.focus();

		}

	}

}
