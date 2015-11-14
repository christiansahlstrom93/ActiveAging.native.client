package com.web.activeagingnativeclient.CommonHelpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.Fragments.HistoryView;
import com.web.activeagingnativeclient.Fragments.ShopView;
import com.web.activeagingnativeclient.Server.ConfirmationHandler;
import com.web.activeagingnativeclient.Server.HistoryHandler;
import com.web.activeagingnativeclient.Server.ShopItems;
import com.web.activeagingnativeclient.ShopItems.Confirmation.ConfirmationActivity;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBagHelper;
import com.web.activeagingnativeclient.Splash;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class TaskHelper {

    public void shopTask() {
        final ShopItems shopItems = new ShopItems();
        if (Splash.progressBar != null) {
            Splash.progressBar.setVisibility(View.VISIBLE);
        }

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    shopItems.getManufactures();
                    shopItems.getProducts();
                    shopItems.getImage();

                } catch (Exception ex) {
                    Log.e(PublicConstants.TAG, "Error i task " + ex);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (Splash.progressBar != null) {
                    Splash.progressBar.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < shopItems.getItemID().size(); i++) {
                    ShopView.getInstance().setListShop(shopItems.getTitle().get(i),
                            shopItems.getDescription().get(i), shopItems.getPrice().get(i),
                            shopItems.getImageUrl().get(i), shopItems.getItemID().get(i));
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
                addOrderToList(id, orderID, items, confirmationActivity);
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
                Toast.makeText(confirmationActivity, "Tack för din beställning", Toast.LENGTH_LONG).show();
                ShopBagHelper.getInstance().clearLists();
                confirmationActivity.finish();
            }
        }.execute();

    }

    public void historyTask(final Context c) {
        final HistoryHandler historyHandler = new HistoryHandler();
        if (Splash.progressBar != null) {
            Splash.progressBar.setVisibility(View.VISIBLE);
        }
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    historyHandler.getOrders(c);
                    historyHandler.getOrderItems(c);
                    historyHandler.getImage();

                } catch (Exception ex) {
                    Log.e(PublicConstants.TAG, "Error i historytask " + ex);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (Splash.progressBar != null) {
                    Splash.progressBar.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < historyHandler.getItemID().size(); i++) {
                    Log.e(PublicConstants.TAG,"Data i loop " + historyHandler.getTitle().get(i) + " " + historyHandler.getImageUrl().get(i));

                    HistoryView.getInstance().setListShop(historyHandler.getTitle().get(i),
                            historyHandler.getDescription().get(i), historyHandler.getPrice().get(i),
                            historyHandler.getImageUrl().get(i), historyHandler.getItemID().get(i));
                }
            }
        }.execute();

    }
}
