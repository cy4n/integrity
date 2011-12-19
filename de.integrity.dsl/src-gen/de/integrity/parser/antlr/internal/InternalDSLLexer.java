package de.integrity.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDSLLexer extends Lexer {
    public static final int RULE_ID=4;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=10;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_STRING=7;
    public static final int T__16=16;
    public static final int T__33=33;
    public static final int T__15=15;
    public static final int T__34=34;
    public static final int T__18=18;
    public static final int T__35=35;
    public static final int T__17=17;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_UPPERCASE_ID=8;
    public static final int RULE_DECIMAL=6;
    public static final int RULE_WS=11;
    public static final int RULE_INTEGER=5;

    // delegates
    // delegators

    public InternalDSLLexer() {;} 
    public InternalDSLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalDSLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g"; }

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:11:7: ( 'packagedef' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:11:9: 'packagedef'
            {
            match("packagedef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:12:7: ( 'with' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:12:9: 'with'
            {
            match("with"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:13:7: ( 'packageend' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:13:9: 'packageend'
            {
            match("packageend"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:14:7: ( 'import' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:14:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:15:7: ( 'testdef' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:15:9: 'testdef'
            {
            match("testdef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:16:7: ( 'uses' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:16:9: 'uses'
            {
            match("uses"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:17:7: ( 'calldef' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:17:9: 'calldef'
            {
            match("calldef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:18:7: ( 'suitedef' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:18:9: 'suitedef'
            {
            match("suitedef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:19:7: ( 'gets' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:19:9: 'gets'
            {
            match("gets"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:20:7: ( 'requires' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:20:9: 'requires'
            {
            match("requires"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:21:7: ( 'suiteend' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:21:9: 'suiteend'
            {
            match("suiteend"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:22:7: ( 'concludedby' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:22:9: 'concludedby'
            {
            match("concludedby"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:23:7: ( 'variable' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:23:9: 'variable'
            {
            match("variable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:24:7: ( 'initially' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:24:9: 'initially'
            {
            match("initially"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:25:7: ( 'test' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:25:9: 'test'
            {
            match("test"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:26:7: ( '=' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:26:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:27:7: ( 'tabletest' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:27:9: 'tabletest'
            {
            match("tabletest"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:28:7: ( '|' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:28:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:29:7: ( '+' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:29:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:30:7: ( 'call' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:30:9: 'call'
            {
            match("call"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:31:7: ( 'sets' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:31:9: 'sets'
            {
            match("sets"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:32:7: ( 'suite' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:32:9: 'suite'
            {
            match("suite"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:33:7: ( ':' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:33:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:34:7: ( '#' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:34:9: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:35:7: ( '.' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:35:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:36:7: ( '.*' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:36:9: '.*'
            {
            match(".*"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "RULE_UPPERCASE_ID"
    public final void mRULE_UPPERCASE_ID() throws RecognitionException {
        try {
            int _type = RULE_UPPERCASE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2191:19: ( 'A' .. 'Z' ( 'A' .. 'Z' | 'a' .. 'z' | '_' | '0' .. '9' )* )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2191:21: 'A' .. 'Z' ( 'A' .. 'Z' | 'a' .. 'z' | '_' | '0' .. '9' )*
            {
            matchRange('A','Z'); 
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2191:30: ( 'A' .. 'Z' | 'a' .. 'z' | '_' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_UPPERCASE_ID"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2193:9: ( ( '^' )? ( 'a' .. 'z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2193:11: ( '^' )? ( 'a' .. 'z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2193:11: ( '^' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='^') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2193:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2193:31: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INTEGER"
    public final void mRULE_INTEGER() throws RecognitionException {
        try {
            int _type = RULE_INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2195:14: ( ( '0' .. '9' )+ )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2195:16: ( '0' .. '9' )+
            {
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2195:16: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2195:17: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INTEGER"

    // $ANTLR start "RULE_DECIMAL"
    public final void mRULE_DECIMAL() throws RecognitionException {
        try {
            int _type = RULE_DECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2197:14: ( RULE_INTEGER '.' RULE_INTEGER )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2197:16: RULE_INTEGER '.' RULE_INTEGER
            {
            mRULE_INTEGER(); 
            match('.'); 
            mRULE_INTEGER(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DECIMAL"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else if ( (LA7_0=='\'') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:66: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='&')||(LA6_0>='(' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2199:137: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2201:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2201:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2201:24: ( options {greedy=false; } : . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<='.')||(LA8_1>='0' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2201:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:40: ( ( '\\r' )? '\\n' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\n'||LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:41: ( '\\r' )? '\\n'
                    {
                    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:41: ( '\\r' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\r') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2203:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2205:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2205:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2205:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2207:16: ( . )
            // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:2207:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:8: ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | RULE_UPPERCASE_ID | RULE_ID | RULE_INTEGER | RULE_DECIMAL | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt13=35;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:10: T__13
                {
                mT__13(); 

                }
                break;
            case 2 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:16: T__14
                {
                mT__14(); 

                }
                break;
            case 3 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:22: T__15
                {
                mT__15(); 

                }
                break;
            case 4 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:28: T__16
                {
                mT__16(); 

                }
                break;
            case 5 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:34: T__17
                {
                mT__17(); 

                }
                break;
            case 6 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:40: T__18
                {
                mT__18(); 

                }
                break;
            case 7 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:46: T__19
                {
                mT__19(); 

                }
                break;
            case 8 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:52: T__20
                {
                mT__20(); 

                }
                break;
            case 9 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:58: T__21
                {
                mT__21(); 

                }
                break;
            case 10 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:64: T__22
                {
                mT__22(); 

                }
                break;
            case 11 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:70: T__23
                {
                mT__23(); 

                }
                break;
            case 12 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:76: T__24
                {
                mT__24(); 

                }
                break;
            case 13 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:82: T__25
                {
                mT__25(); 

                }
                break;
            case 14 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:88: T__26
                {
                mT__26(); 

                }
                break;
            case 15 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:94: T__27
                {
                mT__27(); 

                }
                break;
            case 16 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:100: T__28
                {
                mT__28(); 

                }
                break;
            case 17 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:106: T__29
                {
                mT__29(); 

                }
                break;
            case 18 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:112: T__30
                {
                mT__30(); 

                }
                break;
            case 19 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:118: T__31
                {
                mT__31(); 

                }
                break;
            case 20 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:124: T__32
                {
                mT__32(); 

                }
                break;
            case 21 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:130: T__33
                {
                mT__33(); 

                }
                break;
            case 22 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:136: T__34
                {
                mT__34(); 

                }
                break;
            case 23 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:142: T__35
                {
                mT__35(); 

                }
                break;
            case 24 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:148: T__36
                {
                mT__36(); 

                }
                break;
            case 25 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:154: T__37
                {
                mT__37(); 

                }
                break;
            case 26 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:160: T__38
                {
                mT__38(); 

                }
                break;
            case 27 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:166: RULE_UPPERCASE_ID
                {
                mRULE_UPPERCASE_ID(); 

                }
                break;
            case 28 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:184: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 29 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:192: RULE_INTEGER
                {
                mRULE_INTEGER(); 

                }
                break;
            case 30 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:205: RULE_DECIMAL
                {
                mRULE_DECIMAL(); 

                }
                break;
            case 31 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:218: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 32 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:230: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 33 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:246: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 34 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:262: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 35 :
                // ../de.integrity.dsl/src-gen/de/integrity/parser/antlr/internal/InternalDSL.g:1:270: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\12\33\5\uffff\1\57\1\uffff\1\31\1\uffff\1\61\3\31\2\uffff"+
        "\1\33\1\uffff\15\33\12\uffff\1\61\4\uffff\17\33\1\125\2\33\1\131"+
        "\1\33\1\133\1\135\2\33\1\140\1\141\3\33\1\uffff\3\33\1\uffff\1\33"+
        "\1\uffff\1\33\1\uffff\1\33\1\155\2\uffff\3\33\1\161\7\33\1\uffff"+
        "\3\33\1\uffff\1\33\1\176\1\33\1\u0080\10\33\1\uffff\1\33\1\uffff"+
        "\1\33\1\u008b\1\u008c\1\u008d\1\u008e\2\33\1\u0091\1\u0092\1\33"+
        "\4\uffff\1\u0094\1\u0095\2\uffff\1\33\2\uffff\1\u0097\1\uffff";
    static final String DFA13_eofS =
        "\u0098\uffff";
    static final String DFA13_minS =
        "\1\0\1\141\1\151\1\155\1\141\1\163\1\141\3\145\1\141\5\uffff\1"+
        "\52\1\uffff\1\137\1\uffff\1\56\2\0\1\52\2\uffff\1\143\1\uffff\1"+
        "\164\1\160\1\151\1\163\1\142\1\145\1\154\1\156\1\151\2\164\1\161"+
        "\1\162\12\uffff\1\56\4\uffff\1\153\1\150\1\157\2\164\1\154\1\163"+
        "\1\154\1\143\1\164\2\163\1\165\1\151\1\141\1\60\1\162\1\151\1\60"+
        "\1\145\2\60\1\154\1\145\2\60\1\151\1\141\1\147\1\uffff\1\164\1\141"+
        "\1\145\1\uffff\1\164\1\uffff\1\145\1\uffff\1\165\1\60\2\uffff\1"+
        "\162\1\142\1\145\1\60\1\154\1\146\1\145\1\146\1\144\1\145\1\156"+
        "\1\uffff\1\145\1\154\1\144\1\uffff\1\154\1\60\1\163\1\60\1\145\1"+
        "\146\1\144\1\163\2\145\1\156\1\171\1\uffff\1\164\1\uffff\1\144\4"+
        "\60\1\146\1\144\2\60\1\142\4\uffff\2\60\2\uffff\1\171\2\uffff\1"+
        "\60\1\uffff";
    static final String DFA13_maxS =
        "\1\uffff\1\141\1\151\1\156\1\145\1\163\1\157\1\165\2\145\1\141"+
        "\5\uffff\1\52\1\uffff\1\172\1\uffff\1\71\2\uffff\1\57\2\uffff\1"+
        "\143\1\uffff\1\164\1\160\1\151\1\163\1\142\1\145\1\154\1\156\1\151"+
        "\2\164\1\161\1\162\12\uffff\1\71\4\uffff\1\153\1\150\1\157\2\164"+
        "\1\154\1\163\1\154\1\143\1\164\2\163\1\165\1\151\1\141\1\172\1\162"+
        "\1\151\1\172\1\145\2\172\1\154\1\145\2\172\1\151\1\141\1\147\1\uffff"+
        "\1\164\1\141\1\145\1\uffff\1\164\1\uffff\1\145\1\uffff\1\165\1\172"+
        "\2\uffff\1\162\1\142\1\145\1\172\1\154\1\146\1\145\1\146\1\144\1"+
        "\145\1\156\1\uffff\1\145\1\154\1\145\1\uffff\1\154\1\172\1\163\1"+
        "\172\1\145\1\146\1\144\1\163\2\145\1\156\1\171\1\uffff\1\164\1\uffff"+
        "\1\144\4\172\1\146\1\144\2\172\1\142\4\uffff\2\172\2\uffff\1\171"+
        "\2\uffff\1\172\1\uffff";
    static final String DFA13_acceptS =
        "\13\uffff\1\20\1\22\1\23\1\27\1\30\1\uffff\1\33\1\uffff\1\34\4"+
        "\uffff\1\42\1\43\1\uffff\1\34\15\uffff\1\20\1\22\1\23\1\27\1\30"+
        "\1\32\1\31\1\33\1\35\1\36\1\uffff\1\37\1\40\1\41\1\42\35\uffff\1"+
        "\2\3\uffff\1\17\1\uffff\1\6\1\uffff\1\24\2\uffff\1\25\1\11\13\uffff"+
        "\1\26\3\uffff\1\4\14\uffff\1\5\1\uffff\1\7\12\uffff\1\10\1\13\1"+
        "\12\1\15\2\uffff\1\16\1\21\1\uffff\1\1\1\3\1\uffff\1\14";
    static final String DFA13_specialS =
        "\1\2\24\uffff\1\1\1\0\u0081\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\31\2\30\2\31\1\30\22\31\1\30\1\31\1\25\1\17\3\31\1\26\3"+
            "\31\1\15\2\31\1\20\1\27\12\24\1\16\2\31\1\13\3\31\32\21\3\31"+
            "\1\22\1\23\1\31\2\23\1\6\3\23\1\10\1\23\1\3\6\23\1\1\1\23\1"+
            "\11\1\7\1\4\1\5\1\12\1\2\3\23\1\31\1\14\uff83\31",
            "\1\32",
            "\1\34",
            "\1\35\1\36",
            "\1\40\3\uffff\1\37",
            "\1\41",
            "\1\42\15\uffff\1\43",
            "\1\45\17\uffff\1\44",
            "\1\46",
            "\1\47",
            "\1\50",
            "",
            "",
            "",
            "",
            "",
            "\1\56",
            "",
            "\1\33\1\uffff\32\33",
            "",
            "\1\62\1\uffff\12\63",
            "\0\64",
            "\0\64",
            "\1\65\4\uffff\1\66",
            "",
            "",
            "\1\70",
            "",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62\1\uffff\12\63",
            "",
            "",
            "",
            "",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\126",
            "\1\127",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\3\33\1\130\26\33",
            "\1\132",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\3\33\1\134\26\33",
            "\1\136",
            "\1\137",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\142",
            "\1\143",
            "\1\144",
            "",
            "\1\145",
            "\1\146",
            "\1\147",
            "",
            "\1\150",
            "",
            "\1\151",
            "",
            "\1\152",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\3\33\1\153\1\154"+
            "\25\33",
            "",
            "",
            "\1\156",
            "\1\157",
            "\1\160",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "",
            "\1\171",
            "\1\172",
            "\1\173\1\174",
            "",
            "\1\175",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\177",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "",
            "\1\u0089",
            "",
            "\1\u008a",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u008f",
            "\1\u0090",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\u0093",
            "",
            "",
            "",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "\1\u0096",
            "",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | RULE_UPPERCASE_ID | RULE_ID | RULE_INTEGER | RULE_DECIMAL | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_22 = input.LA(1);

                        s = -1;
                        if ( ((LA13_22>='\u0000' && LA13_22<='\uFFFF')) ) {s = 52;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_21 = input.LA(1);

                        s = -1;
                        if ( ((LA13_21>='\u0000' && LA13_21<='\uFFFF')) ) {s = 52;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='p') ) {s = 1;}

                        else if ( (LA13_0=='w') ) {s = 2;}

                        else if ( (LA13_0=='i') ) {s = 3;}

                        else if ( (LA13_0=='t') ) {s = 4;}

                        else if ( (LA13_0=='u') ) {s = 5;}

                        else if ( (LA13_0=='c') ) {s = 6;}

                        else if ( (LA13_0=='s') ) {s = 7;}

                        else if ( (LA13_0=='g') ) {s = 8;}

                        else if ( (LA13_0=='r') ) {s = 9;}

                        else if ( (LA13_0=='v') ) {s = 10;}

                        else if ( (LA13_0=='=') ) {s = 11;}

                        else if ( (LA13_0=='|') ) {s = 12;}

                        else if ( (LA13_0=='+') ) {s = 13;}

                        else if ( (LA13_0==':') ) {s = 14;}

                        else if ( (LA13_0=='#') ) {s = 15;}

                        else if ( (LA13_0=='.') ) {s = 16;}

                        else if ( ((LA13_0>='A' && LA13_0<='Z')) ) {s = 17;}

                        else if ( (LA13_0=='^') ) {s = 18;}

                        else if ( (LA13_0=='_'||(LA13_0>='a' && LA13_0<='b')||(LA13_0>='d' && LA13_0<='f')||LA13_0=='h'||(LA13_0>='j' && LA13_0<='o')||LA13_0=='q'||(LA13_0>='x' && LA13_0<='z')) ) {s = 19;}

                        else if ( ((LA13_0>='0' && LA13_0<='9')) ) {s = 20;}

                        else if ( (LA13_0=='\"') ) {s = 21;}

                        else if ( (LA13_0=='\'') ) {s = 22;}

                        else if ( (LA13_0=='/') ) {s = 23;}

                        else if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {s = 24;}

                        else if ( ((LA13_0>='\u0000' && LA13_0<='\b')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\u001F')||LA13_0=='!'||(LA13_0>='$' && LA13_0<='&')||(LA13_0>='(' && LA13_0<='*')||(LA13_0>=',' && LA13_0<='-')||(LA13_0>=';' && LA13_0<='<')||(LA13_0>='>' && LA13_0<='@')||(LA13_0>='[' && LA13_0<=']')||LA13_0=='`'||LA13_0=='{'||(LA13_0>='}' && LA13_0<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}