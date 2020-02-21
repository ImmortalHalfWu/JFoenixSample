package wu.ui.adapters;

//
//public class BaseListViewAdapter extends ListCell<Node> {
//    @Override
//    protected void updateItem(Node item, boolean empty) {
//        if (!empty) {
//            setGraphic(item);
//        }
//        super.updateItem(item, empty);
//    }
//}

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class BaseListViewAdapter<itemView extends Node> implements Callback<ListView<itemView>, ListCell<itemView>> {
    @Override
    public ListCell<itemView> call(ListView<itemView> param) {
        return  new ListCell<itemView>() {
            @Override
            protected void updateItem(itemView item, boolean empty) {
                if (!empty) {
                    setGraphic(item);
                }
                super.updateItem(item, empty);
            }
        };
    }
}
