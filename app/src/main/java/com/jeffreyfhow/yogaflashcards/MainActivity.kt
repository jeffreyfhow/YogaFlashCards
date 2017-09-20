package com.jeffreyfhow.yogaflashcards
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import JsonParser
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import com.jeffreyfhow.yogaflashcards.JsonToPose.PoseBuilder
import android.view.MenuItem
import android.view.Menu

/**
 * Swiping through cards via dragging or buttons
 */
class MainActivity : AppCompatActivity() {

    private lateinit var deck: Deck
    private lateinit var helpCard: CardView
    /**
     * Create the deck of Pose Cards from json data
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create Deck with Json Data
        val jsonObject = JsonParser.jsonFileToJsonArr("/res/raw/yoga_data_test.json")
        deck = Deck(this, PoseBuilder.createPoses(jsonObject))

        val myToolbar: Toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(myToolbar)

        helpCard = findViewById<CardView>(R.id.main_help_card)
        helpCard.visibility = CardView.INVISIBLE

    }

    /**
     * Click handler for 'Okay' button
     */
    fun onTrash(v:View){
        if(deck.isInteractable) deck.trash(true)
    }

    /**
     * Click handler for 'Cancel' button
     */
    fun onKeep(v:View){
        if(deck.isInteractable) deck.keep(true)
    }

    /**
     * Click handler for 'Show' button
     */
    fun onShow(v:View){
        if(deck.isInteractable) deck.reveal()
    }

    /**
     * Click handler for 'Back' button
     */
    fun onBack(v: View){
        helpCard.visibility = CardView.INVISIBLE
    }

    /**
     * Attach menu to toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     * Toolbar Handler
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_help -> {
                helpCard.visibility = CardView.VISIBLE
                return true
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
