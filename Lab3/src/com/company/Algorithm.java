package com.company;

import java.util.List;

public interface Algorithm {
    public void solve();
    public double getRunningTime();
    public void setAvailableItems(Item...allItems);
    public void setAvailableItemsList(List<Item> items);
}
