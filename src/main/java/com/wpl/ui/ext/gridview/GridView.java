package com.wpl.ui.ext.gridview;

import java.awt.Component;

import javax.swing.JTable;

import com.wpl.ui.components.IComponent;
import com.wpl.ui.ext.factory.GridViewFactory;
import com.wpl.ui.factory.annotations.DefaultFactory;

@DefaultFactory(GridViewFactory.class)
public class GridView<T> implements IComponent {

	private JTable mTable;
	private final GridViewTableModel<T> mTableModel;

	public GridView() {
		this.mTableModel = new GridViewTableModel<T>();
	}

	public void clearData() {
		mTableModel.clearData();
	}

	public void addData(final T data) {
		mTableModel.addData(data);
	}

	public GridViewColumnModel getColumnModel() {
		return mTableModel.getColumnModel();
	}

	@Override
	public Component getComponent() {
		this.mTable = mTableModel.createTable();
		return this.mTable;
	}
}
