package logiqueDeJeux;

import java.util.EventListener;

import machineEtat.*;

public interface GlobalListener extends EventListener
{
	void nouvelleCarte(CardEvent carte);
	void nouveauGeste(MouvementEvent mouvement);
	void finPli();
}
