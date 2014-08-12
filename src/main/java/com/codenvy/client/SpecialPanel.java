package com.codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.view.client.ListDataProvider;


public class SpecialPanel extends Composite {

    interface SpecialPanelUiBinder extends UiBinder<DockLayoutPanel, SpecialPanel> {
    }

    private static SpecialPanelUiBinder ourUiBinder = GWT.create(SpecialPanelUiBinder.class);

    @UiField CellTable table;
    @UiField Button first;
    @UiField Button second;
    @UiField Button third;

    TextColumn<SpecialText> firstColumn = new TextColumn<SpecialText>() {
        @Override
        public String getValue(SpecialText text) {
            return text.getFirstText();
        }
    };

    TextColumn<SpecialText> secondColumn = new TextColumn<SpecialText>() {
        @Override
        public String getValue(SpecialText text) {
            return text.getSecondText();
        }
    };

    TextColumn<SpecialText> thirdColumn = new TextColumn<SpecialText>() {
        @Override
        public String getValue(SpecialText text) {
            return text.getThirdText();
        }
    };

    public SpecialPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));
        table.addColumn(firstColumn);
        table.setColumnWidth(0, 60, Style.Unit.PX);
        table.addColumn(secondColumn);
        table.setColumnWidth(1, 60, Style.Unit.PX);
        table.addColumn(thirdColumn);
        table.setColumnWidth(2, 60, Style.Unit.PX);
        ListDataProvider<SpecialText> provider = new ListDataProvider<SpecialText>();
        provider.addDataDisplay(table);

        for (int i=1;i<=10;i++) {
            provider.getList().add(new SpecialText("one", "two", "three"));
        }

        MyCss res = Resourse.IMPL.css();
        res.ensureInjected();

        first.addStyleName(res.button());
        second.addStyleName(res.button());
        third.addStyleName(res.button());
    }

    @UiHandler("first")
    void clickFirstButton(ClickEvent event) {
        Window.alert("First");
    }

    @UiHandler("second")
    void clickSecondButton(ClickEvent event) {
        Window.alert("Second");
    }

    @UiHandler("third")
    void clickThirdButton(ClickEvent event) {
        Window.alert("Third");
    }

    @UiHandler("first")
    void mouseOverFirst(MouseOverEvent event) {
        first.setText("ClickMe!");
    }

    @UiHandler("first")
    void mouseOutFirst(MouseOutEvent event) {
        first.setText("first");
    }

    class SpecialText {
        private String firstText;
        private String secondText;
        private String thirdText;

        SpecialText(String firstText, String secondText, String thirdText){
            this.firstText=firstText;
            this.secondText=secondText;
            this.thirdText=thirdText;
        }
        public String getFirstText() {
            return firstText;
        }

        public void setFirstText(String firstText) {
            this.firstText = firstText;
        }

        public String getSecondText() {
            return secondText;
        }

        public void setSecondText(String secondText) {
            this.secondText = secondText;
        }

        public String getThirdText() {
            return thirdText;
        }

        public void setThirdText(String thirdText) {
            this.thirdText = thirdText;
        }
    }
}