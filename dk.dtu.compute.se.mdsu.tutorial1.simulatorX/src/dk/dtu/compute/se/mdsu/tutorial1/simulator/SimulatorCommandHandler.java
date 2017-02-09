package dk.dtu.compute.se.mdsu.tutorial1.simulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

// TODO replace these imports by your own classes (which
//      you generated from your model
import dk.dtu.compute.mbse.petrinet.Arc;
import dk.dtu.compute.mbse.petrinet.Node;
import dk.dtu.compute.mbse.petrinet.PetrinetFactory;
import dk.dtu.compute.mbse.petrinet.Place;
import dk.dtu.compute.mbse.petrinet.Token;
import dk.dtu.compute.mbse.petrinet.Transition;
import dk.dtu.compute.se.mdsu.tutorial1.commands.FireTransitionCommand;

public class SimulatorCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Transition transition = getTransition(event.getApplicationContext());
		if (isEnabled(transition)) {
			EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(transition);
			if(domain !=null){
				domain.getCommandStack().execute(new FireTransitionCommand(domain,transition));
			}
			fire(transition);
		}
		return null;
	}

	@Override
	public void setEnabled(Object context) {
		Transition transition = getTransition(context);
		setBaseEnabled(isEnabled(transition));
	}
	
	static private Transition getTransition(Object context) {
		if (context instanceof IEvaluationContext) {
			IEvaluationContext evaluationContext = (IEvaluationContext) context;
			Object object = evaluationContext.getDefaultVariable();
			if (object instanceof List) {
				@SuppressWarnings("rawtypes")
				List list = (List) object;
				if (list.size() == 1) {
					object = list.get(0);
					if (object instanceof Transition) {
						return (Transition) object;
					}
				}
			}
		}
		return null;
	}
	
	static private boolean isEnabled(Transition transition) {
		
		if(transition !=null){
			
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
						
						// check whether each place has the number of needed tokens
						for (Place place: needed.keySet()) {
							if (place.getTokens().size() < needed.get(place)) {
								return false;
							}
						}
						return true;
					}
			
		// TODO compute whether there are enough tokens on
		//      each input place of a transition; Note that
		//      there could be more than one arc between the
		//      same place and the same transition (
      
		return false;
	}

	static private void fire(Transition transition) {
		
		if(transition !=null){
			
			// remove the token from each places of the incoming arcs
						for (Arc arc: transition.getIn()) {
							Node node = arc.getSource();
							if (node instanceof Place) {
								Place place = (Place) node;
								List<Token> tokens = place.getTokens();
								if (!tokens.isEmpty()) {
									place.getTokens().remove(0);
								}
							}
						}
			
						// add one token to each outgoing place
						for (Arc arc: transition.getOut()) {
							Node node = arc.getTarget();
							if (node instanceof Place) {
								Place place = (Place) node;
								Token token = PetrinetFactory.eINSTANCE.createToken();
								place.getTokens().add(token);
							}
						}	
			
			
		}
		
		// TODO create code which fires a transition, i.e.
		//      removes one token for each incoming arc from
		//      the target place and adds one token to the
		//      target place of each outgoing arc.
		
		// Note: don't use new Token() or new TokenIml() for
		//       creating a now token object use the factory
		//       that was generated from the model. This could
		//      look like:
        //
		// Token token = PetrinetFactory.eINSTANCE.createToken();
					
	}
	



}
