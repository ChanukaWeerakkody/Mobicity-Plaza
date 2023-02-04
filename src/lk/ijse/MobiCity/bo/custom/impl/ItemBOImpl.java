package lk.ijse.MobiCity.bo.custom.impl;

import lk.ijse.MobiCity.bo.custom.ItemBO;
import lk.ijse.MobiCity.dao.DAOFactory;
import lk.ijse.MobiCity.dao.custom.ItemDAO;
import lk.ijse.MobiCity.dto.ItemDTO;
import lk.ijse.MobiCity.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private ItemDAO itemDAO=(ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO>allItem=new ArrayList<>();
        for (Item item : all) {
            allItem.add(new ItemDTO(item.getItemId(),item.getName(),item.getBrand(),item.getQty(),item.getPrice()));
        }
        return allItem;
    }
    @Override
    public void saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        itemDAO.save(new Item(dto.getItemId(), dto.getName(), dto.getBrand(), dto.getQty(),dto.getPrice()));
    }
    @Override
    public boolean existsItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }
    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        Item ent = itemDAO.search(id);
        return new ItemDTO(ent.getItemId(), ent.getName(), ent.getBrand(), ent.getQty(),ent.getPrice());
    }
    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
    @Override
    public void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        itemDAO.update(new Item(dto.getItemId(), dto.getName(), dto.getBrand(), dto.getQty(),dto.getPrice()));
    }
}
