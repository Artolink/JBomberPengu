package model;

/**
 * Second interface of the game, used for every generic destructible entity, extends {@link IndestructibleEntity}.
 */
public interface DestructibleEntity extends Entity {

    /**
     * Method that defines if the entity is destroyed or not.
     * 
     * @return true if destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     * Sets the status of the entity (1 if destroyed, 0 otherwise).
     * 
     * @param destroyed defines if the Entity has been destroyed or not
     */
    void setStatus(boolean destroyed);

    //METODO PER RILASCIARE QUALCOSA (ES.BOMBA ESPLOSIONE, BLOCCHI POWERUP.., OPZIONALE IMMAGINO)
}
