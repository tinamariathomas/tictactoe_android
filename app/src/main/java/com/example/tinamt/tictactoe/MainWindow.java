package com.example.tinamt.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


public class MainWindow extends ActionBarActivity {

    int turn;
    String turnCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        LinearLayout ll = (LinearLayout) this.findViewById(R.id.mainLayout);
        TableLayout innerLayout = new TableLayout(this);
        ll.addView(innerLayout);
        turn = 0;
        turnCharacter="X";

        Button buttons[][] = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setText("-");
                buttons[i][j].setWidth(30);
                buttons[i][j].setHeight(30);
                buttons[i][j].setId(Integer.parseInt(Integer.toString(i + 1) + Integer.toString(j + 1)));

                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tr.addView(buttons[i][j]);

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button t = (Button) v;
                        Log.d("Grid:", Integer.toString(t.getId()));
                        markBox(t);
                        if (checkForWin(t)) {
                            Log.d("Winning", "done");
                            //Disable buttons
                            //Display who has won
                        }

                        toggleTurn();
                    }

                    private void markBox(Button t) {
                        String currentText = t.getText().toString();
                        t.setText(alternateDisplayedCharacter(currentText, turn));
                    }

                    private String alternateDisplayedCharacter(String currentText, int turn) {
                        switch (currentText.charAt(0)) {
                            case '-':
                                if (turn == 0)
                                    return "X";
                                else
                                    return "O";
                            default:
                                return currentText;
                        }
                    }

                });
            }
            innerLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private void toggleTurn() {

        turn = (turn == 0) ? 1 : 0;
        turnCharacter = (turnCharacter == "X") ? "O" : "X";

    }

    boolean checkForWin(Button t) {
        String currentBoxID = Integer.toString(t.getId());//[i,j] of the clicked box
        boolean horizontalLine = checkHorizontalLine(currentBoxID.charAt(0), currentBoxID.charAt(1));
        boolean verticalLine = checkVerticalLine(currentBoxID.charAt(0), currentBoxID.charAt(1));
        System.out.println("asdfad");
        return horizontalLine || verticalLine || checkDiagonal(currentBoxID.charAt(0), currentBoxID.charAt(1));
    }

    private boolean checkHorizontalLine(char x, char y) {
        int xInt = Integer.parseInt(String.valueOf(x));
        int yInt = Integer.parseInt(String.valueOf(y));


        for (int i = 0; i < 3; i++) {
            Button v = (Button) findViewById(Integer.parseInt(Integer.toString(xInt) + Integer.toString(i + 1)));
            String text = (String) v.getText();
            if (text != turnCharacter)
                return false;
        }
        return true;
    }

    private boolean checkDiagonal(char x, char y) {
        int xInt = Integer.parseInt(String.valueOf(x));
        int yInt = Integer.parseInt(String.valueOf(y));
        boolean val1=true,val2=true;
        if (!(xInt == yInt || xInt + yInt == 4))
            return false;

        for (int i = 0; i < 3; i++) {
            Button v = (Button) findViewById(Integer.parseInt(Integer.toString(i + 1) + Integer.toString(i + 1)));
            String text = (String) v.getText();
            if (text != turnCharacter) {
                val1 = false;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            Button v = (Button) findViewById(Integer.parseInt(Integer.toString(i + 1) + Integer.toString(3 - i)));
            String text = (String) v.getText();
            if (text != turnCharacter) {
                val2 = false;
                break;
            }
        }
        return val1||val2;
    }

    private boolean checkVerticalLine(char x, char y) {
        int xInt = Integer.parseInt(String.valueOf(x));
        int yInt = Integer.parseInt(String.valueOf(y));

        for (int i = 0; i < 3; i++) {
            Button v = (Button) findViewById(Integer.parseInt(Integer.toString(i + 1) + Integer.toString(yInt)));
            String text = (String) v.getText();
            if (text != turnCharacter)
                return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_window, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
