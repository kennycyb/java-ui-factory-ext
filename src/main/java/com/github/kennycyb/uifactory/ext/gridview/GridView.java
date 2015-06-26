package com.github.kennycyb.uifactory.ext.gridview;

import java.awt.Component;

import javax.swing.JTable;

import com.github.kennycyb.uifactory.core.components.IComponent;
import com.github.kennycyb.uifactory.core.factory.annotations.DefaultFactory;
import com.github.kennycyb.uifactory.ext.factory.GridViewFactory;

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
