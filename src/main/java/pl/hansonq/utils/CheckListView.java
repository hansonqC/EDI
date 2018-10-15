//package pl.hansonq.utils;
//
//import javafx.beans.property.BooleanProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.ListChangeListener;
//import javafx.collections.ObservableList;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javafx.scene.control.cell.CheckBoxListCell;
//import javafx.util.Callback;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class CheckListView<T> extends ListView<T> {
//
//    /**************************************************************************
//     *
//     * Private fields
//     *
//     **************************************************************************/
//
//    private final Map<T, BooleanProperty> itemBooleanMap;
//
//
//
//    /**************************************************************************
//     *
//     * Constructors
//     *
//     **************************************************************************/
//
//    /**
//     * Creates a new CheckListView instance with an empty list of choices.
//     */
//    public CheckListView() {
//        this(null);
//    }
//
//    /**
//     * Creates a new CheckListView instance with the given items available as
//     * choices.
//     *
//     * @param items The items to display within the CheckListView.
//     */
//    public CheckListView(ObservableList<T> items) {
//        super(items);
//        this.itemBooleanMap = new HashMap<>();
//
//        setCheckModel(new CheckListViewBitSetCheckModel<,>(getItems(), itemBooleanMap));
//        itemsProperty().addListener(ov -> {
//            setCheckModel(new CheckListViewBitSetCheckModel<>(getItems(), itemBooleanMap));
//        });
//
//        setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
//            public ListCell<T> call(ListView<T> listView) {
//                return new CheckBoxListCell<T>(new Callback<T, ObservableValue<Boolean>>() {
//                    @Override public ObservableValue<Boolean> call(T item) {
//                        return getItemBooleanProperty(item);
//                    }
//                });
//            };
//        });
//    }
//
//
//
//    /**************************************************************************
//     *
//     * Public API
//     *
//     **************************************************************************/
//
//    /**
//     * Returns the {@link BooleanProperty} for a given item index in the
//     * CheckListView. This is useful if you want to bind to the property.
//     */
//    public BooleanProperty getItemBooleanProperty(int index) {
//        if (index < 0 || index >= getItems().size()) return null;
//        return getItemBooleanProperty(getItems().get(index));
//    }
//
//    /**
//     * Returns the {@link BooleanProperty} for a given item in the
//     * CheckListView. This is useful if you want to bind to the property.
//     */
//    public BooleanProperty getItemBooleanProperty(T item) {
//        return itemBooleanMap.get(item);
//    }
//
//
//
//    /**************************************************************************
//     *
//     * Properties
//     *
//     **************************************************************************/
//
//    // --- Check Model
//    private ObjectProperty<IndexedCheckModel<T>> checkModel =
//            new SimpleObjectProperty<>(this, "checkModel"); //$NON-NLS-1$
//
//    /**
//     * Sets the 'check model' to be used in the CheckListView - this is the
//     * code that is responsible for representing the selected state of each
//     * {@link CheckBox} - that is, whether each {@link CheckBox} is checked or
//     * not (and not to be confused with the
//     * selection model concept, which is used in the ListView control to
//     * represent the selection state of each row)..
//     */
//    public final void setCheckModel(IndexedCheckModel<T> value) {
//        checkModelProperty().set(value);
//    }
//
//    /**
//     * Returns the currently installed check model.
//     */
//    public final IndexedCheckModel<T> getCheckModel() {
//        return checkModel == null ? null : checkModel.get();
//    }
//
//    /**
//     * The check model provides the API through which it is possible
//     * to check single or multiple items within a CheckListView, as  well as inspect
//     * which items have been checked by the user. Note that it has a generic
//     * type that must match the type of the CheckListView itself.
//     */
//    public final ObjectProperty<IndexedCheckModel<T>> checkModelProperty() {
//        return checkModel;
//    }
//
//
//
//    /**************************************************************************
//     *
//     * Implementation
//     *
//     **************************************************************************/
//
//
//
//
//    /**************************************************************************
//     *
//     * Support classes
//     *
//     **************************************************************************/
//
//    private static class CheckListViewBitSetCheckModel<T> extends CheckBitSetModelBase<T> {
//
//        /***********************************************************************
//         *                                                                     *
//         * Internal properties                                                 *
//         *                                                                     *
//         **********************************************************************/
//
//        private final ObservableList<T> items;
//
//
//
//        /***********************************************************************
//         *                                                                     *
//         * Constructors                                                        *
//         *                                                                     *
//         **********************************************************************/
//
//        CheckListViewBitSetCheckModel(final ObservableList<T> items, final Map<T, BooleanProperty> itemBooleanMap) {
//            super(itemBooleanMap);
//
//            this.items = items;
//            this.items.addListener(new ListChangeListener<T>() {
//                @Override public void onChanged(Change<? extends T> c) {
//                    updateMap();
//                }
//            });
//
//            updateMap();
//        }
//
//
//        @Override public T getItem(int index) {
//            return items.get(index);
//        }
//
//        @Override public int getItemCount() {
//            return items.size();
//        }
//
//        @Override public int getItemIndex(T item) {
//            return items.indexOf(item);
//        }
//    }
//}
