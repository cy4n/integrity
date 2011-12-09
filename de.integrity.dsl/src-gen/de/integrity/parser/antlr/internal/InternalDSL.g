/*
* generated by Xtext
*/
grammar InternalDSL;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package de.integrity.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package de.integrity.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.integrity.services.DSLGrammarAccess;

}

@parser::members {

 	private DSLGrammarAccess grammarAccess;
 	
    public InternalDSLParser(TokenStream input, DSLGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Model";	
   	}
   	
   	@Override
   	protected DSLGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleModel
entryRuleModel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getModelRule()); }
	 iv_ruleModel=ruleModel 
	 { $current=$iv_ruleModel.current; } 
	 EOF 
;

// Rule Model
ruleModel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getStatementsStatementParserRuleCall_0()); 
	    }
		lv_statements_0_0=ruleStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		add(
       			$current, 
       			"statements",
        		lv_statements_0_0, 
        		"Statement");
	        afterParserOrEnumRuleCall();
	    }

)
)*
;





// Entry rule entryRuleStatement
entryRuleStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStatementRule()); }
	 iv_ruleStatement=ruleStatement 
	 { $current=$iv_ruleStatement.current; } 
	 EOF 
;

// Rule Statement
ruleStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getStatementAccess().getPackageDefinitionParserRuleCall_0()); 
    }
    this_PackageDefinition_0=rulePackageDefinition
    { 
        $current = $this_PackageDefinition_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getStatementAccess().getImportParserRuleCall_1()); 
    }
    this_Import_1=ruleImport
    { 
        $current = $this_Import_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getStatementAccess().getSuiteParserRuleCall_2()); 
    }
    this_Suite_2=ruleSuite
    { 
        $current = $this_Suite_2.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRulePackageDefinition
entryRulePackageDefinition returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPackageDefinitionRule()); }
	 iv_rulePackageDefinition=rulePackageDefinition 
	 { $current=$iv_rulePackageDefinition.current; } 
	 EOF 
;

// Rule PackageDefinition
rulePackageDefinition returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='packagedef' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getPackageDefinitionAccess().getPackagedefKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPackageDefinitionAccess().getNameQualifiedNameParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPackageDefinitionRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='with' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getPackageDefinitionAccess().getWithKeyword_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPackageDefinitionAccess().getStatementsPackageStatementParserRuleCall_3_0()); 
	    }
		lv_statements_3_0=rulePackageStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPackageDefinitionRule());
	        }
       		add(
       			$current, 
       			"statements",
        		lv_statements_3_0, 
        		"PackageStatement");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_4='packageend' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getPackageDefinitionAccess().getPackageendKeyword_4());
    }
)
;





// Entry rule entryRulePackageStatement
entryRulePackageStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPackageStatementRule()); }
	 iv_rulePackageStatement=rulePackageStatement 
	 { $current=$iv_rulePackageStatement.current; } 
	 EOF 
;

// Rule PackageStatement
rulePackageStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getPackageStatementAccess().getImportParserRuleCall_0()); 
    }
    this_Import_0=ruleImport
    { 
        $current = $this_Import_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getPackageStatementAccess().getTestDefinitionParserRuleCall_1()); 
    }
    this_TestDefinition_1=ruleTestDefinition
    { 
        $current = $this_TestDefinition_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getPackageStatementAccess().getCallDefinitionParserRuleCall_2()); 
    }
    this_CallDefinition_2=ruleCallDefinition
    { 
        $current = $this_CallDefinition_2.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getPackageStatementAccess().getSuiteDefinitionParserRuleCall_3()); 
    }
    this_SuiteDefinition_3=ruleSuiteDefinition
    { 
        $current = $this_SuiteDefinition_3.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getPackageStatementAccess().getVariableDefinitionParserRuleCall_4()); 
    }
    this_VariableDefinition_4=ruleVariableDefinition
    { 
        $current = $this_VariableDefinition_4.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleImport
entryRuleImport returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getImportRule()); }
	 iv_ruleImport=ruleImport 
	 { $current=$iv_ruleImport.current; } 
	 EOF 
;

// Rule Import
ruleImport returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='import' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0()); 
	    }
		lv_importedNamespace_1_0=ruleQualifiedNameWithWildcard		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getImportRule());
	        }
       		set(
       			$current, 
       			"importedNamespace",
        		lv_importedNamespace_1_0, 
        		"QualifiedNameWithWildcard");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleTestDefinition
entryRuleTestDefinition returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getTestDefinitionRule()); }
	 iv_ruleTestDefinition=ruleTestDefinition 
	 { $current=$iv_ruleTestDefinition.current; } 
	 EOF 
;

// Rule TestDefinition
ruleTestDefinition returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='testdef' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getTestDefinitionAccess().getTestdefKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getTestDefinitionAccess().getNameQualifiedNameParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTestDefinitionRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='uses' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getTestDefinitionAccess().getUsesKeyword_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getTestDefinitionAccess().getFixtureMethodMethodReferenceParserRuleCall_3_0()); 
	    }
		lv_fixtureMethod_3_0=ruleMethodReference		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTestDefinitionRule());
	        }
       		set(
       			$current, 
       			"fixtureMethod",
        		lv_fixtureMethod_3_0, 
        		"MethodReference");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleCallDefinition
entryRuleCallDefinition returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getCallDefinitionRule()); }
	 iv_ruleCallDefinition=ruleCallDefinition 
	 { $current=$iv_ruleCallDefinition.current; } 
	 EOF 
;

// Rule CallDefinition
ruleCallDefinition returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='calldef' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getCallDefinitionAccess().getCalldefKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getCallDefinitionAccess().getNameQualifiedNameParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getCallDefinitionRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='uses' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getCallDefinitionAccess().getUsesKeyword_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getCallDefinitionAccess().getFixtureMethodMethodReferenceParserRuleCall_3_0()); 
	    }
		lv_fixtureMethod_3_0=ruleMethodReference		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getCallDefinitionRule());
	        }
       		set(
       			$current, 
       			"fixtureMethod",
        		lv_fixtureMethod_3_0, 
        		"MethodReference");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleSuiteDefinition
entryRuleSuiteDefinition returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSuiteDefinitionRule()); }
	 iv_ruleSuiteDefinition=ruleSuiteDefinition 
	 { $current=$iv_ruleSuiteDefinition.current; } 
	 EOF 
;

// Rule SuiteDefinition
ruleSuiteDefinition returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='suitedef' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getSuiteDefinitionAccess().getSuitedefKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getSuiteDefinitionAccess().getNameQualifiedNameParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSuiteDefinitionRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_2='gets' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getSuiteDefinitionAccess().getGetsKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getSuiteDefinitionAccess().getParametersVariableEntityParserRuleCall_2_1_0()); 
	    }
		lv_parameters_3_0=ruleVariableEntity		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSuiteDefinitionRule());
	        }
       		add(
       			$current, 
       			"parameters",
        		lv_parameters_3_0, 
        		"VariableEntity");
	        afterParserOrEnumRuleCall();
	    }

)
)*)?(	otherlv_4='requires' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getSuiteDefinitionAccess().getRequiresKeyword_3_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getSuiteDefinitionRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getSuiteDefinitionAccess().getDependenciesSuiteDefinitionCrossReference_3_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)*)?	otherlv_6='with' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getSuiteDefinitionAccess().getWithKeyword_4());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getSuiteDefinitionAccess().getStatementsSuiteStatementParserRuleCall_5_0()); 
	    }
		lv_statements_7_0=ruleSuiteStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSuiteDefinitionRule());
	        }
       		add(
       			$current, 
       			"statements",
        		lv_statements_7_0, 
        		"SuiteStatement");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_8='suiteend' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getSuiteDefinitionAccess().getSuiteendKeyword_6());
    }
(	otherlv_9='concludedby' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getSuiteDefinitionAccess().getConcludedbyKeyword_7_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getSuiteDefinitionRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getSuiteDefinitionAccess().getFinalizersSuiteDefinitionCrossReference_7_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)*)?)
;





// Entry rule entryRuleSuiteStatement
entryRuleSuiteStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSuiteStatementRule()); }
	 iv_ruleSuiteStatement=ruleSuiteStatement 
	 { $current=$iv_ruleSuiteStatement.current; } 
	 EOF 
;

// Rule SuiteStatement
ruleSuiteStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getSuiteStatementAccess().getSuiteStatementWithResultParserRuleCall_0()); 
    }
    this_SuiteStatementWithResult_0=ruleSuiteStatementWithResult
    { 
        $current = $this_SuiteStatementWithResult_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getSuiteStatementAccess().getCallParserRuleCall_1()); 
    }
    this_Call_1=ruleCall
    { 
        $current = $this_Call_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getSuiteStatementAccess().getVariableDefinitionParserRuleCall_2()); 
    }
    this_VariableDefinition_2=ruleVariableDefinition
    { 
        $current = $this_VariableDefinition_2.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleSuiteStatementWithResult
entryRuleSuiteStatementWithResult returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSuiteStatementWithResultRule()); }
	 iv_ruleSuiteStatementWithResult=ruleSuiteStatementWithResult 
	 { $current=$iv_ruleSuiteStatementWithResult.current; } 
	 EOF 
;

// Rule SuiteStatementWithResult
ruleSuiteStatementWithResult returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getSuiteStatementWithResultAccess().getSuiteParserRuleCall_0()); 
    }
    this_Suite_0=ruleSuite
    { 
        $current = $this_Suite_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getSuiteStatementWithResultAccess().getTestParserRuleCall_1()); 
    }
    this_Test_1=ruleTest
    { 
        $current = $this_Test_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleVariableDefinition
entryRuleVariableDefinition returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getVariableDefinitionRule()); }
	 iv_ruleVariableDefinition=ruleVariableDefinition 
	 { $current=$iv_ruleVariableDefinition.current; } 
	 EOF 
;

// Rule VariableDefinition
ruleVariableDefinition returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='variable' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getVariableDefinitionAccess().getVariableKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getVariableDefinitionAccess().getNameVariableEntityParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleVariableEntity		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getVariableDefinitionRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"VariableEntity");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_2='initially' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getVariableDefinitionAccess().getInitiallyKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getVariableDefinitionAccess().getInitialValueValueParserRuleCall_2_1_0()); 
	    }
		lv_initialValue_3_0=ruleValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getVariableDefinitionRule());
	        }
       		set(
       			$current, 
       			"initialValue",
        		lv_initialValue_3_0, 
        		"Value");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleVariableEntity
entryRuleVariableEntity returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getVariableEntityRule()); }
	 iv_ruleVariableEntity=ruleVariableEntity 
	 { $current=$iv_ruleVariableEntity.current; } 
	 EOF 
;

// Rule VariableEntity
ruleVariableEntity returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getVariableEntityAccess().getNameQualifiedNameParserRuleCall_0()); 
	    }
		lv_name_0_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getVariableEntityRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_0_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleTest
entryRuleTest returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getTestRule()); }
	 iv_ruleTest=ruleTest 
	 { $current=$iv_ruleTest.current; } 
	 EOF 
;

// Rule Test
ruleTest returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='test' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getTestAccess().getTestKeyword_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTestRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getTestAccess().getDefinitionTestDefinitionCrossReference_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getTestAccess().getParametersParameterParserRuleCall_2_0()); 
	    }
		lv_parameters_2_0=ruleParameter		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTestRule());
	        }
       		add(
       			$current, 
       			"parameters",
        		lv_parameters_2_0, 
        		"Parameter");
	        afterParserOrEnumRuleCall();
	    }

)
)*(	otherlv_3='=' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getTestAccess().getEqualsSignKeyword_3_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getTestAccess().getResultValueOrEnumValueParserRuleCall_3_1_0()); 
	    }
		lv_result_4_0=ruleValueOrEnumValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTestRule());
	        }
       		set(
       			$current, 
       			"result",
        		lv_result_4_0, 
        		"ValueOrEnumValue");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleCall
entryRuleCall returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getCallRule()); }
	 iv_ruleCall=ruleCall 
	 { $current=$iv_ruleCall.current; } 
	 EOF 
;

// Rule Call
ruleCall returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='call' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getCallAccess().getCallKeyword_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getCallRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getCallAccess().getDefinitionCallDefinitionCrossReference_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getCallAccess().getParametersParameterParserRuleCall_2_0()); 
	    }
		lv_parameters_2_0=ruleParameter		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getCallRule());
	        }
       		add(
       			$current, 
       			"parameters",
        		lv_parameters_2_0, 
        		"Parameter");
	        afterParserOrEnumRuleCall();
	    }

)
)*(	otherlv_3='sets' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getCallAccess().getSetsKeyword_3_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getCallAccess().getResultVariableParserRuleCall_3_1_0()); 
	    }
		lv_result_4_0=ruleVariable		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getCallRule());
	        }
       		set(
       			$current, 
       			"result",
        		lv_result_4_0, 
        		"Variable");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleSuite
entryRuleSuite returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSuiteRule()); }
	 iv_ruleSuite=ruleSuite 
	 { $current=$iv_ruleSuite.current; } 
	 EOF 
;

// Rule Suite
ruleSuite returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='suite' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getSuiteAccess().getSuiteKeyword_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getSuiteRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getSuiteAccess().getDefinitionSuiteDefinitionCrossReference_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getSuiteAccess().getParametersSuiteParameterParserRuleCall_2_0()); 
	    }
		lv_parameters_2_0=ruleSuiteParameter		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSuiteRule());
	        }
       		add(
       			$current, 
       			"parameters",
        		lv_parameters_2_0, 
        		"SuiteParameter");
	        afterParserOrEnumRuleCall();
	    }

)
)*)
;





// Entry rule entryRuleSuiteParameter
entryRuleSuiteParameter returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSuiteParameterRule()); }
	 iv_ruleSuiteParameter=ruleSuiteParameter 
	 { $current=$iv_ruleSuiteParameter.current; } 
	 EOF 
;

// Rule SuiteParameter
ruleSuiteParameter returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getSuiteParameterRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getSuiteParameterAccess().getNameVariableEntityCrossReference_0_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_1=':' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getSuiteParameterAccess().getColonKeyword_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getSuiteParameterAccess().getValueValueParserRuleCall_2_0()); 
	    }
		lv_value_2_0=ruleValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSuiteParameterRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_2_0, 
        		"Value");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleParameter
entryRuleParameter returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getParameterRule()); }
	 iv_ruleParameter=ruleParameter 
	 { $current=$iv_ruleParameter.current; } 
	 EOF 
;

// Rule Parameter
ruleParameter returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getParameterAccess().getNameParameterNameParserRuleCall_0_0()); 
	    }
		lv_name_0_0=ruleParameterName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_0_0, 
        		"ParameterName");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_1=':' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getParameterAccess().getColonKeyword_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getParameterAccess().getValueValueOrEnumValueParserRuleCall_2_0()); 
	    }
		lv_value_2_0=ruleValueOrEnumValue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_2_0, 
        		"ValueOrEnumValue");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleParameterName
entryRuleParameterName returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getParameterNameRule()); }
	 iv_ruleParameterName=ruleParameterName 
	 { $current=$iv_ruleParameterName.current; } 
	 EOF 
;

// Rule ParameterName
ruleParameterName returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getParameterNameAccess().getFixedParameterNameParserRuleCall_0()); 
    }
    this_FixedParameterName_0=ruleFixedParameterName
    { 
        $current = $this_FixedParameterName_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getParameterNameAccess().getArbitraryParameterNameParserRuleCall_1()); 
    }
    this_ArbitraryParameterName_1=ruleArbitraryParameterName
    { 
        $current = $this_ArbitraryParameterName_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleFixedParameterName
entryRuleFixedParameterName returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getFixedParameterNameRule()); }
	 iv_ruleFixedParameterName=ruleFixedParameterName 
	 { $current=$iv_ruleFixedParameterName.current; } 
	 EOF 
;

// Rule FixedParameterName
ruleFixedParameterName returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getFixedParameterNameRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getFixedParameterNameAccess().getAnnotationJvmAnnotationReferenceCrossReference_0()); 
	}

)
)
;





// Entry rule entryRuleArbitraryParameterName
entryRuleArbitraryParameterName returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getArbitraryParameterNameRule()); }
	 iv_ruleArbitraryParameterName=ruleArbitraryParameterName 
	 { $current=$iv_ruleArbitraryParameterName.current; } 
	 EOF 
;

// Rule ArbitraryParameterName
ruleArbitraryParameterName returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='+' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getArbitraryParameterNameAccess().getPlusSignKeyword_0());
    }
(
(
		lv_identifier_1_0=RULE_ID
		{
			newLeafNode(lv_identifier_1_0, grammarAccess.getArbitraryParameterNameAccess().getIdentifierIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getArbitraryParameterNameRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"identifier",
        		lv_identifier_1_0, 
        		"ID");
	    }

)
))
;





// Entry rule entryRuleValueOrEnumValue
entryRuleValueOrEnumValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getValueOrEnumValueRule()); }
	 iv_ruleValueOrEnumValue=ruleValueOrEnumValue 
	 { $current=$iv_ruleValueOrEnumValue.current; } 
	 EOF 
;

// Rule ValueOrEnumValue
ruleValueOrEnumValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getValueOrEnumValueAccess().getValueParserRuleCall_0()); 
    }
    this_Value_0=ruleValue
    { 
        $current = $this_Value_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getValueOrEnumValueAccess().getEnumValueParserRuleCall_1()); 
    }
    this_EnumValue_1=ruleEnumValue
    { 
        $current = $this_EnumValue_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleValue
entryRuleValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getValueRule()); }
	 iv_ruleValue=ruleValue 
	 { $current=$iv_ruleValue.current; } 
	 EOF 
;

// Rule Value
ruleValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getValueAccess().getStringValueParserRuleCall_0()); 
    }
    this_StringValue_0=ruleStringValue
    { 
        $current = $this_StringValue_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getValueAccess().getIntegerValueParserRuleCall_1()); 
    }
    this_IntegerValue_1=ruleIntegerValue
    { 
        $current = $this_IntegerValue_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getValueAccess().getDecimalValueParserRuleCall_2()); 
    }
    this_DecimalValue_2=ruleDecimalValue
    { 
        $current = $this_DecimalValue_2.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getValueAccess().getVariableParserRuleCall_3()); 
    }
    this_Variable_3=ruleVariable
    { 
        $current = $this_Variable_3.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleIntegerValue
entryRuleIntegerValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getIntegerValueRule()); }
	 iv_ruleIntegerValue=ruleIntegerValue 
	 { $current=$iv_ruleIntegerValue.current; } 
	 EOF 
;

// Rule IntegerValue
ruleIntegerValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_integerValue_0_0=RULE_INTEGER
		{
			newLeafNode(lv_integerValue_0_0, grammarAccess.getIntegerValueAccess().getIntegerValueINTEGERTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getIntegerValueRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"integerValue",
        		lv_integerValue_0_0, 
        		"INTEGER");
	    }

)
)
;





// Entry rule entryRuleDecimalValue
entryRuleDecimalValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getDecimalValueRule()); }
	 iv_ruleDecimalValue=ruleDecimalValue 
	 { $current=$iv_ruleDecimalValue.current; } 
	 EOF 
;

// Rule DecimalValue
ruleDecimalValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_decimalValue_0_0=RULE_DECIMAL
		{
			newLeafNode(lv_decimalValue_0_0, grammarAccess.getDecimalValueAccess().getDecimalValueDECIMALTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getDecimalValueRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"decimalValue",
        		lv_decimalValue_0_0, 
        		"DECIMAL");
	    }

)
)
;





// Entry rule entryRuleStringValue
entryRuleStringValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStringValueRule()); }
	 iv_ruleStringValue=ruleStringValue 
	 { $current=$iv_ruleStringValue.current; } 
	 EOF 
;

// Rule StringValue
ruleStringValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_stringValue_0_0=RULE_STRING
		{
			newLeafNode(lv_stringValue_0_0, grammarAccess.getStringValueAccess().getStringValueSTRINGTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getStringValueRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"stringValue",
        		lv_stringValue_0_0, 
        		"STRING");
	    }

)
)
;





// Entry rule entryRuleVariable
entryRuleVariable returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getVariableRule()); }
	 iv_ruleVariable=ruleVariable 
	 { $current=$iv_ruleVariable.current; } 
	 EOF 
;

// Rule Variable
ruleVariable returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getVariableRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getVariableAccess().getNameVariableEntityCrossReference_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleEnumValue
entryRuleEnumValue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getEnumValueRule()); }
	 iv_ruleEnumValue=ruleEnumValue 
	 { $current=$iv_ruleEnumValue.current; } 
	 EOF 
;

// Rule EnumValue
ruleEnumValue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getEnumValueRule());
	        }
        }
	otherlv_0=RULE_UPPERCASE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getEnumValueAccess().getEnumValueJvmEnumerationLiteralCrossReference_0()); 
	}

)
)
;





// Entry rule entryRuleMethodReference
entryRuleMethodReference returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMethodReferenceRule()); }
	 iv_ruleMethodReference=ruleMethodReference 
	 { $current=$iv_ruleMethodReference.current; } 
	 EOF 
;

// Rule MethodReference
ruleMethodReference returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMethodReferenceRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getMethodReferenceAccess().getTypeJvmTypeCrossReference_0_0()); 
	    }
		ruleQualifiedJavaClassName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_1='#' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getMethodReferenceAccess().getNumberSignKeyword_1());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMethodReferenceRule());
	        }
        }
	otherlv_2=RULE_ID
	{
		newLeafNode(otherlv_2, grammarAccess.getMethodReferenceAccess().getMethodJvmOperationCrossReference_2_0()); 
	}

)
))
;





// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameRule()); } 
	 iv_ruleQualifiedName=ruleQualifiedName 
	 { $current=$iv_ruleQualifiedName.current.getText(); }  
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
    }
(
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
    }
    this_ID_2=RULE_ID    {
		$current.merge(this_ID_2);
    }

    { 
    newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
    }
)*)
    ;





// Entry rule entryRuleQualifiedJavaClassName
entryRuleQualifiedJavaClassName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedJavaClassNameRule()); } 
	 iv_ruleQualifiedJavaClassName=ruleQualifiedJavaClassName 
	 { $current=$iv_ruleQualifiedJavaClassName.current.getText(); }  
	 EOF 
;

// Rule QualifiedJavaClassName
ruleQualifiedJavaClassName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getQualifiedJavaClassNameAccess().getQualifiedNameParserRuleCall_0()); 
    }
    this_QualifiedName_0=ruleQualifiedName    {
		$current.merge(this_QualifiedName_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }

	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedJavaClassNameAccess().getFullStopKeyword_1()); 
    }
    this_UPPERCASE_ID_2=RULE_UPPERCASE_ID    {
		$current.merge(this_UPPERCASE_ID_2);
    }

    { 
    newLeafNode(this_UPPERCASE_ID_2, grammarAccess.getQualifiedJavaClassNameAccess().getUPPERCASE_IDTerminalRuleCall_2()); 
    }
)
    ;





// Entry rule entryRuleQualifiedNameWithWildcard
entryRuleQualifiedNameWithWildcard returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); } 
	 iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard 
	 { $current=$iv_ruleQualifiedNameWithWildcard.current.getText(); }  
	 EOF 
;

// Rule QualifiedNameWithWildcard
ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0()); 
    }
    this_QualifiedName_0=ruleQualifiedName    {
		$current.merge(this_QualifiedName_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }
(
	kw='.*' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1()); 
    }
)?)
    ;





RULE_UPPERCASE_ID : 'A'..'Z' ('A'..'Z'|'a'..'z'|'_'|'0'..'9')*;

RULE_ID : '^'? ('a'..'z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INTEGER : ('0'..'9')+;

RULE_DECIMAL : RULE_INTEGER '.' RULE_INTEGER;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


