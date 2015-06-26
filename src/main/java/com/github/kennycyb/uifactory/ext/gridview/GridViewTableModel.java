package com.github.kennycyb.uifactory.ext.gridview;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridViewTableModel<T> extends AbstractTableModel {

	private static Logger LOGGER = LoggerFactory.getLogger(GridViewTableModel.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -2690213448788265466L;

	private final GridViewColumnModel mColumnModel;
	private final List<T> mData = new ArrayList<T>();

	public GridViewTableModel() {
		mColumnModel = new GridViewColumnModel();
	}

	public GridViewColumnModel getColumnModel() {
		return mColumnModel;
	}

	// ~ ROW --------------------------

	@Override
	public int getRowCount() {
		return mData.size();
	}

	// ~ COLUMN ------------------------

	@Override
	public int getColumnCount() {
		return mColumnModel.getColumnCount();
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		final GridViewColumn column = (GridViewColumn) mColumnModel.getColumn(columnIndex);
		return column.getFieldType();
	}

	@Override
	public String getColumnName(final int column) {
		final GridViewColumn c = (GridViewColumn) mColumnModel.getColumn(column);
		return c.getFieldName();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final T object = mData.get(rowIndex);
		if (object == null) {
			return null;
		}

		final GridViewColumn column = (GridViewColumn) mColumnModel.getColumn(columnIndex);
		return column.getValue(object);
	}

	public void addData(final T data) {
		final int firstRow = mData.size();
		mData.add(data);
		final int lastRow = mData.size();
		fireTableRowsInserted(firstRow, lastRow);
	}

	public void clearData() {
		mData.clear();
		fireTableDataChanged();
	}

	public JTable createTable() {
		return new JTable(this, mColumnModel);
	}
}
