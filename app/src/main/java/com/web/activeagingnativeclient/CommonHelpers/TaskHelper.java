package com.web.activeagingnativeclient.CommonHelpers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.Fragments.ShopView;
import com.web.activeagingnativeclient.Server.ConfirmationHandler;
import com.web.activeagingnativeclient.Server.ShopItems;
import com.web.activeagingnativeclient.ShopItems.Confirmation.ConfirmationActivity;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBagHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class TaskHelper {

    public void shopTask() {
        final ShopItems shopItems = new ShopItems();
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    shopItems.getManufactures();
                    shopItems.getProducts();
                    shopItems.getImage();

                    ShopView.getInstance().setDescription(shopItems.getDescription());
                    ShopView.getInstance().setImageUrl(shopItems.getImageUrl());
                    ShopView.getInstance().setPrice(shopItems.getPrice());
                    ShopView.getInstance().setTitle(shopItems.getTitle());
                    ShopView.getInstance().setItemID(shopItems.getItemID());
                } catch (Exception ex) {
                    Log.e(PublicConstants.TAG, "Error i task " + ex);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                for (int i = 0; i < ShopView.getInstance().getItemID().size(); i++) {
                    ShopView.getInstance().setListShop(ShopView.getInstance().getTitle().get(i),
                            ShopView.getInstance().getDescription().get(i), ShopView.getInstance().getPrice().get(i),
                            ShopView.getInstance().getImageUrl().get(i));
                }
            }
        }.execute();

    }

    public void createOrder(final int id, final List<Integer> orderID, final List<Float> items, final ConfirmationActivity confirmationActivity) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                Log.e(PublicConstants.TAG, "CREATE ORDER");
                for (int i = 0; i < items.size(); i++) {
                    try {
                        int order;
                        ConfirmationHandler confirmationHandler = new ConfirmationHandler();
                        while ((order = confirmationHandler.postOrder(id)) == PublicConstants.ORDER_FAIL) {
                            confirmationHandler = new ConfirmationHandler();
                        }
                        orderID.add(order);
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        Log.e(PublicConstants.TAG, "Error i create " + e);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                addOrderToList(id, orderID, items,confirmationActivity);
            }
        }.execute();

    }

    public void addOrderToList(final int id, final List<Integer> orderID, final List<Float> items, final ConfirmationActivity confirmationActivity) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                Log.e(PublicConstants.TAG, "ORDER TO LIST " + orderID.size());
                for (int i = 0; i < orderID.size(); i++) {
                    try {

                        ConfirmationHandler confirmationHandler = new ConfirmationHandler();
                        while ((confirmationHandler.addItemToOrder(orderID.get(i), id, items.get(i))) == PublicConstants.ORDER_FAIL) {
                            confirmationHandler = new ConfirmationHandler();
                        }
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        Log.e(PublicConstants.TAG, "Error i order list " + e);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(confirmationActivity,"Tack för din beställning",Toast.LENGTH_LONG).show();
                ShopBagHelper.getInstance().clearLists();
                confirmationActivity.finish();
            }
        }.execute();

    }

}
