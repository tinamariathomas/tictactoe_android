package com.example.tinamt.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


public class MainWindow extends ActionBarActivity {

    int turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        LinearLayout ll = (LinearLayout) this.findViewById(R.id.mainLayout);
        TableLayout innerLayout = new TableLayout(this);
        ll.addView(innerLayout);
        turn = 0;

        Button buttons[][] = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setText("-");
                buttons[i][j].setWidth(30);
                buttons[i][j].setHeight(30);

                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tr.addView(buttons[i][j]);

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button t = (Button) v;
                        String currentText = t.getText().toString();
                        t.setText(alternateDisplayedCharacter(currentText, turn));
                        turn = (turn == 0) ? 1 : 0;
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
