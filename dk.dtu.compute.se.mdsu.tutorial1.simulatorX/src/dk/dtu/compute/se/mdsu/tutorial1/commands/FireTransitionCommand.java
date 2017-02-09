package dk.dtu.compute.se.mdsu.tutorial1.commands;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import dk.dtu.compute.mbse.petrinet.Arc;
import dk.dtu.compute.mbse.petrinet.Node;
import dk.dtu.compute.mbse.petrinet.PetrinetFactory;
import dk.dtu.compute.mbse.petrinet.PetrinetPackage;
import dk.dtu.compute.mbse.petrinet.Place;
import dk.dtu.compute.mbse.petrinet.Token;
import dk.dtu.compute.mbse.petrinet.Transition;

public class FireTransitionCommand extends CompoundCommand {
	public FireTransitionCommand(EditingDomain domain, Transition transition){
		
		// compute the number of tokens needed for each place for firing
		// the given transition and check whether the place has that many tokens
		
		Map<Place, Integer> needed = new HashMap<Place,Integer>();
		for (Arc arc: transition.getIn()) {
			Node node = arc.getSource();
			if (node instanceof Place) {
				Place source = (Place) node;
				needed.put(source, needed.getOrDefault(source, 0) + 1);
			}
		}
		
		//add commands for removind the needed number of token from each place
		
		for (Place place:needed.keySet()){
			for(int i=0; i<needed.get(place)&& i<place.getTokens().size(); i++)
				this.append(new RemoveCommand(domain, place, PetrinetPackage.eINSTANCE.getPlace_Tokens(),place.getTokens().get(i)));
		}
		// add commands for adding a token to each target place of each output arc
		for (Arc arc: transition.getOut()){
			Node node = arc.getTarget();
			if (node instanceof Place){
				Place place = (Place)node;
				Token token = PetrinetFactory.eINSTANCE.createToken();
				this.append(new CreateChildCommand(domain, place, PetrinetPackage.eINSTANCE.getPlace_Tokens(), token, null));
			}
		}
	}
}

