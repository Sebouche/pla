package ricm3.parser;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import ricm3.interpreter.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {

	// All this is only for the graphical .dot output of the Abstract Syntax Tree 

	public String kind; 	// the name of the non-terminal node 

	public int id = Id.fresh(); // a unique id used as a graph node 

	// AST as tree
	
	public String dot_id(){ 
		return Dot.node_id(this.id) ;
	}
	
	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.dot_id(), this.dot_id()) + this.as_dot_tree() ;
	}
	
	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}
	
	public String as_tree_node() {
		return Dot.declare_node(this.dot_id(), this.kind, "");
	}
	
	public String tree_edges() {
		return "undefined: " + this.kind + ".tree_edges" ; 
	}

	// AST as automata in .dot format
	
	public String as_dot_aut() {
		return "undefined " + this.kind + ".as_dot_aut";
	}
	
	// AST as active automata (interpreter of transitions)
	
	public Object make() {
		List<IAutomaton> automate = new LinkedList<IAutomaton>();
		AI_Definitions aidef = (AI_Definitions) this;
		automate = aidef.make();
		return automate;
	}
	
	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.kind = "Terminal" ;
			this.value = string;
		}

		public String toString() {
			return value ;
		}
		
		public String tree_edges() {
			String value_id = Dot.node_id( -this.id) ;
			return Dot.declare_node( value_id, value, "shape=none, fontsize=10, fontcolor=blue" ) + Dot.edge(this.dot_id(), value_id) ;
		}
		
		public String make() {
			return value;

		}
	}

	// Value = Constant U Variable
	
	public static class Value extends Ast {}

	public static class Constant extends Value {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
		
		public String toString() {
			return value.toString() ;
		}
		
		public String make() {
			return value.make();

		}
	}

	public static class Variable extends Value {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}
	
		public String toString() {
			return name.toString() ;
		}
		
		public String make() {
			return name.make();

		}
	}

	// Parameter = Underscore U Key U Direction U Entity 
	// Parameter are not Expression (no recursion) 
	
	public static abstract class Parameter extends Ast {}

	public static class Underscore extends Parameter {
		Underscore(){
			this.kind = "Underscore" ;
		}
		public String tree_edges() {
			return "" ;
		}
		public String toString() {
			return "_";
		}
		
		public String make() {
			return "_";

		}
	}
	
	public static class Number_as_String extends Parameter {
		
		Constant value;
		
		Number_as_String(String string){
			this.kind = "Number" ;
			this.value = new Constant(string);
		}
		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
		
		public String toString() { 
			return value.toString() ; 
		}
		
		public Object make() {
			return Integer.parseInt(value.make());

		}
	}
	
	public static class Key extends Parameter {

		Constant value;

		Key(String string) {
			this.kind = "Key";
			this.value = new Constant(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
		
		public String toString() { 
			return value.toString() ; 
		}
		
		public String make() {
			return value.make();

		}
	}

	public static class Direction extends Parameter {

		Value value;

		Direction(Value value) {
			this.kind = "Direction";
			this.value = value;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
		
		public String toString() { 
			return value.toString() ; 
		}
		
		public String make() {
			if (value instanceof Constant) {
				return ((Constant) value).make();
			} else {
				return ((Variable) value).make();
			}
		}
	}

	public static class Entity extends Parameter {

		Value value;

		Entity(Value expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}
		
		public String toString() { 
			return value.toString() ; 
		}
		
		public String make() {
			if (value instanceof Constant) {
				return ((Constant) value).make();
			} else {
				return ((Variable) value).make();
			}
		}
	}

	// Expression = UnaryOp Expression U  Expression BinaryOp Expression U FunCall(Parameters) 
	
	public static abstract class Expression extends Ast {
		public abstract String toString();
	}
	
	public static class None extends Expression {
		None(){}
		public final String toString() { return "none" ; }
		public String tree_edges() { return "" ; } 
		
	}
	
	public static class UnaryOp extends Expression {

		Terminal operator;
		Expression operand;

		UnaryOp(String operator, Expression operand) {
			this.kind = "UnaryOp";
			this.operator = new Terminal(operator);
			this.operand = operand;
		}

		public String tree_edges() {
			return operator.as_tree_son_of(this) + operand.as_tree_son_of(this);
		}
		
		public String toString() { 
			return operator + "(" + operand + ")" ; 
		}
		
		public ICondition make() {
			ICondition cond = new ICondition();
			cond.op = new IOperator(operator.make());
			if (operand instanceof FunCall) {
				cond.expr1 = (ICondition) ((FunCall) operand).make();
			} else if (operand instanceof BinaryOp) {
				cond.expr1 = ((BinaryOp) operand).make();
			} else {
				cond.expr1 = ((UnaryOp) operand).make();
			}
			return cond;

		}
	}

	public static class BinaryOp extends Expression {

		Terminal operator;
		Expression left_operand;
		Expression right_operand;

		BinaryOp(Expression l, String operator, Expression r) {
			this.kind = "BinaryOp";
			this.operator = new Terminal(operator);
			this.left_operand = l;
			this.right_operand = r;
		}

		public String tree_edges() {
			return left_operand.as_tree_son_of(this) + operator.as_tree_son_of(this)
					+ right_operand.as_tree_son_of(this);
		}
		
		public String toString() { 
			return "(" + left_operand + " " + operator + " " + right_operand + ")" ; 
		}
		
public IExpression make() {
			
			IAction act = null;
			ICondition cond = null;
			IExpression tmp = new IExpression();
			if (left_operand instanceof FunCall) {
				tmp = ((FunCall) left_operand).make();
				if(tmp instanceof IAction) {
					act = new IAction();
					act.op = new IOperator(operator.make());
					act.act = tmp;
				}
				else {
					cond = new ICondition();
					cond.op = new IOperator(operator.make());
					cond.expr1 = tmp;
				}
				
			} else if (left_operand instanceof BinaryOp) {
				tmp = ((BinaryOp) left_operand).make();
				if(tmp instanceof IAction) {
					act = new IAction();
					act.op = new IOperator(operator.make());
					act.act = tmp;
				}
				else {
					cond = new ICondition();
					cond.op = new IOperator(operator.make());
					cond.expr1 = tmp;
				}
			} else {
				tmp = ((UnaryOp) left_operand).make();
				if(tmp instanceof IAction) {
					act = new IAction();
					act.op = new IOperator(operator.make());
					act.act = tmp;
				}
				else {
					cond = new ICondition();
					cond.op = new IOperator(operator.make());
					cond.expr1 = tmp;
				}
			}
			if (right_operand instanceof FunCall) {
				tmp = ((FunCall) right_operand).make();
				if(tmp instanceof IAction) {
					act.act = tmp;
				}
				else {
					cond.expr2 = tmp;
				}
				
			} else if (right_operand instanceof BinaryOp) {
				tmp = ((BinaryOp) right_operand).make();
				if(tmp instanceof IAction) {
					act.act = tmp;
				}
				else {
					cond.expr2 = tmp;
				}
			} else {
				tmp = ((UnaryOp) right_operand).make();
				if(tmp instanceof IAction) {
					act.act = tmp;
				}
				else {
					cond.expr2 = tmp;
				}
			}
			if (cond == null) return (IExpression) act;
			return (IExpression) cond;
		}
	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Parameter> parameters;

		FunCall(String name, List<Parameter> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				output += parameter.as_tree_son_of(this);
			}
			return output;
		}
		
		public String toString() { 
			String string = new String();
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				string += parameter.toString();
				if (Iter.hasNext()) { string += "," ;} 
			}
			return name + "(" + string + ")" ; 
		}
		
		public IExpression make() {
			switch (name.make()) {
			case ("True"):
				return new True();
			case ("Key"):
				return new KeyP(((Key) parameters.get(0)).make());
			case ("MyDir"):
				return new MyDir(((Direction) parameters.get(0)).make());
			case ("Cell"):
				if (parameters.size() == 2) {
					return new Cell( (String) parameters.get(0).make(),  (String) parameters.get(1).make());
				} else
					return new Cell( (String) parameters.get(0).make(),  (String) parameters.get(1).make(),
							(int) ((Number_as_String) parameters.get(2)).make());
			case ("Closest"):
				return new Closest(((Entity) parameters.get(0)).make(), ((Direction) parameters.get(1)).make());
			case ("GotPower"):
				return new GotPower();
			case ("GotStuff"):
				return new GotStuff();

			case ("Wait"):
				return new Wait();
			case ("Pop"):
				if (parameters.size() == 1) {
					return new Pop(((Direction) parameters.get(0)).make());
				}
				return new Pop();
			case ("Wizz"):
				if (parameters.size() == 1) {
					return new Wizz(((Direction) parameters.get(0)).make());
				}
				return new Wizz();
			case ("Move"):
				if (parameters.size() == 1) {
					return new Move(((Direction) parameters.get(0)).make());
				}
				return new Move();
			case ("Jump"):
				if (parameters.size() == 1) {
					return new Jump(((Direction) parameters.get(0)).make());
				}
				return new Jump();
			case ("Turn"):
				if (parameters.size() == 1) {
					return new Turn(((Direction) parameters.get(0)).make());
				}
				return new Turn();
			case ("Hit"):
				if (parameters.size() == 1) {
					return new Hit(((Direction) parameters.get(0)).make());
				} else if (parameters.size() == 2) {
					return new Hit(((Direction) parameters.get(0)).make(),
							(int) ((Number_as_String) parameters.get(1)).make());
				}
				return new Hit();
			case ("Protect"):
				if (parameters.size() == 1) {
					return new Protect(((Direction) parameters.get(0)).make());
				}
				return new Protect();
			case ("Pick"):
				if (parameters.size() == 1) {
					return new Pick(((Direction) parameters.get(0)).make());
				}
				return new Pick();
			case ("Throw"):
				if (parameters.size() == 1) {
					return new Throw(((Direction) parameters.get(0)).make());
				}
				return new Throw();
			case ("Store"):
				return new Store();
			case ("Get"):
				return new Get();
			case ("Power"):
				return new Power();
			case ("Kamikaze"):
				return new Kamikaze();
			case ("Egg"):
				return new Egg();
			default:
				return null;
			}
		}
	}

	public static class Condition extends Ast {

		Expression expression;

		Condition(Expression expression) {
			this.kind = "Condition";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}
		
		public String toString() {
			return expression.toString() ;
		}
		
		public ICondition make() {
			if (expression instanceof FunCall) {
				ICondition cond = new ICondition();
				cond.expr1 = (ICondition) ((FunCall) expression).make();
				return cond;

			} else if (expression instanceof BinaryOp) {
				return (ICondition) ((BinaryOp) expression).make();
			} else {
				return ((UnaryOp) expression).make();
			}

		}
	}

	public static class Action extends Ast {

		Expression expression;

		Action(){
			this.kind = "Action" ;
			this.expression = new None();
		}
		Action(Expression expression) {
			this.kind = "Action";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}
		
		public String toString() {
			return expression.toString() ;
		}
		
		public IAction make() {
			if(expression instanceof FunCall) {
				IAction act = new IAction();
				act.act = ((IAction)expression.make());
				return act;
			}
			else if(expression instanceof BinaryOp){
				return (IAction) ((BinaryOp) expression).make();
			}
			else{
				return null;
			}
		}
	}

	public static class State extends Ast {

		Terminal name;

		State(String string) {
			this.kind = "State";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}
		
		public String dot_id_of_state_of(Automaton automaton){ 
			return Dot.name( automaton.id + "." + name.toString() ) ;
		}
		
		public String as_state_of(Automaton automaton){ 
			return Dot.declare_node( this.dot_id_of_state_of(automaton), name.toString(), "shape=circle, fontsize=4") ;
		}
		
		public IState make() {
			return new IState(name.make());

		}
	}

	public static class AI_Definitions extends Ast {

		List<Automaton> automata;

		AI_Definitions(List<Automaton> list) {
			this.kind = "AI_Definitions";
			this.automata = list;
		}

		public String tree_edges() {
			String output = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				output += automaton.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_tree() {
			return Dot.graph("AST", this.as_tree_node() + this.tree_edges());
		}

		public String as_dot_aut() {
			String string = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				string += automaton.as_dot_aut();
			}
			return Dot.graph("Automata", string);
		}
		
		public List<IAutomaton> make() {
			Iterator<Automaton> iter = automata.iterator();
			List<IAutomaton> Automates = new LinkedList<IAutomaton>();
			Automaton current;
			while (iter.hasNext()) {
				current = iter.next();
				Automates.add(current.make());
			}

			return Automates;
		}
		
	}
	
	public static class Automaton extends Ast {

		Terminal name;
		State entry;
		List<Behaviour> behaviours;

		Automaton(String name, State entry, List<Behaviour> behaviours) {
			this.kind = "Automaton";
			this.name = new Terminal(name);
			this.entry = entry;
			this.behaviours = behaviours;
		}

	
		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			output += entry.as_tree_son_of(this);
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				output += behaviour.as_tree_son_of(this);
			}
			return output;
		}
		
		public String as_dot_aut() {
			String string = new String();
			string += Dot.declare_node(this.dot_id(), name.toString(), "shape=box, fontcolor=red") ;
			string += Dot.edge(this.dot_id(), entry.dot_id_of_state_of(this)) ;
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				string += behaviour.as_transition_of(this);
			}
			return Dot.subgraph(this.id, string) ;
		}
		
		public IAutomaton make() {
			String nom = name.make();
			IState etat = entry.make();
			Iterator<Behaviour> iter = behaviours.iterator();
			List<IBehaviour> lbehaviour = new LinkedList<IBehaviour>();
			Behaviour current;
			while (iter.hasNext()) {
				current = iter.next();
				lbehaviour.add(current.make());
			}
			return new IAutomaton(nom, etat, lbehaviour);
		}
		
	}

	public static class Behaviour extends Ast {

		State source;
		List<Transition> transitions;

		Behaviour(State state, List<Transition> transitions) {
			this.kind = "Behaviour";
			this.source = state;
			this.transitions = transitions;
		}

		public String tree_edges() {
			String output = new String();
			output += source.as_tree_son_of(this);
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				output += transition.as_tree_son_of(this);
			}
			return output;
		}
		
		public String as_transition_of(Automaton automaton) {
			String string = new String();
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				string += transition.as_transition_from(automaton, source);
			}
			return source.as_state_of(automaton) + string ;
		}
		
		public IBehaviour make() {
			IState etat = source.make();
			List<ITransition> transition = new LinkedList<ITransition>();
			Iterator<Transition> iter = transitions.iterator();
			Transition current;
			while (iter.hasNext()) {
				current = iter.next();
				transition.add(current.make());
			}

			return new IBehaviour(etat, transition);

		}
	}

	public static class Transition extends Ast {

		Condition condition;
		Action action;
		State target;

		Transition(Condition condition, Action action, State target) {
			this.kind = "Transition";
			this.condition = condition;
			this.action = action;
			this.target = target;
		}

		/*
		public ITransition make() {
			return new ITransition(condition.make(), action.make(), target.make());
		}
		*/
		
		public String tree_edges() {
			return condition.as_tree_son_of(this) + action.as_tree_son_of(this) + target.as_tree_son_of(this);
		}
		
		public String toString() {
			return condition + "? " + action ;
		}
		
		public String as_transition_from(Automaton automaton, State source) {
			String string = new String();
			string += Dot.declare_node( this.dot_id() , this.toString(), "shape=box, fontcolor=blue, fontsize=6") ;
			string += Dot.edge(source.dot_id_of_state_of(automaton), this.dot_id()) ;
			string += Dot.edge(this.dot_id(), target.dot_id_of_state_of(automaton)) ;
			return string ;
		}
		

		public ITransition make() {
			ICondition cond = condition.make();
			IAction act = action.make();
			IState etat = target.make();
			return new ITransition(cond, act, etat);

		}
	}
}
