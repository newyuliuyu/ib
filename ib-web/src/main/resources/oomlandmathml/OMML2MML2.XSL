<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mml="http://www.w3.org/1998/Math/MathML"
	xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math">
	<xsl:output method="xml" encoding="UTF-16" />

	<!-- %% Global Definitions -->
	
	<!-- Every single unicode character that is recognized by OMML as an operator -->
	<xsl:variable name="sOperators"
		select="concat(
					'&#x0021;&#x0022;&#x0028;&#x0029;&#x002B;&#x002C;&#x002D;&#x002F;&#x2AFE;&#x003A;&#x003B;&#x003C;',
					'&#x003D;&#x003E;&#x003F;&#x005B;&#x005C;&#x005D;&#x007B;&#x007C;&#x007D;&#x00A1;&#x00AC;&#x00B1;',
					'&#x00B7;&#x00BF;&#x00D7;&#x00F7;&#x2000;&#x2001;&#x2002;&#x2003;&#x2004;&#x2005;&#x2006;&#x2009;',
					'&#x200A;&#x2010;&#x2012;&#x2013;&#x2014;&#x2016;&#x2020;&#x2021;&#x2022;&#x2024;&#x2025;&#x2026;',
					'&#x203C;&#x2040;&#x204E;&#x204F;&#x2050;&#x205f;&#x2061;&#x2062;&#x2063;&#x2140;&#x2190;&#x2191;',
					'&#x2192;&#x2193;&#x2194;&#x2195;&#x2196;&#x2197;&#x2198;&#x2199;&#x219A;&#x219B;&#x219C;&#x219D;',
					'&#x219E;&#x219F;&#x21A0;&#x21A1;&#x21A2;&#x21A3;&#x21A4;&#x21A5;&#x21A6;&#x21A7;&#x21A8;&#x21A9;',
					'&#x21AA;&#x21AB;&#x21AC;&#x21AD;&#x21AE;&#x21AF;&#x21B0;&#x21B1;&#x21B2;&#x21B3;&#x21B6;&#x21B7;',
					'&#x21BA;&#x21BB;&#x21BC;&#x21BD;&#x21BE;&#x21BF;&#x21C0;&#x21C1;&#x21C2;&#x21C3;&#x21C4;&#x21C5;',
					'&#x21C6;&#x21C7;&#x21C8;&#x21C9;&#x21CA;&#x21CB;&#x21CC;&#x21CD;&#x21CE;&#x21CF;&#x21D0;&#x21D1;',
					'&#x21D2;&#x21D3;&#x21D4;&#x21D5;&#x21D6;&#x21D7;&#x21D8;&#x21D9;&#x21DA;&#x21DB;&#x21DC;&#x21DD;',
					'&#x21DE;&#x21DF;&#x21E0;&#x21E1;&#x21E2;&#x21E3;&#x21E4;&#x21E5;&#x21E6;&#x21E7;&#x21E8;&#x21E9;',
					'&#x21F4;&#x21F5;&#x21F6;&#x21F7;&#x21F8;&#x21F9;&#x21FA;&#x21FB;&#x21FC;&#x21FD;&#x21FE;&#x21FF;',
					'&#x2200;&#x2201;&#x2202;&#x2203;&#x2204;&#x2206;&#x2207;&#x2208;&#x2209;&#x220A;&#x220B;&#x220C;',
					'&#x220D;&#x220F;&#x2210;&#x2211;&#x2212;&#x2213;&#x2214;&#x2215;&#x2216;&#x2217;&#x2218;&#x2219;',
					'&#x221A;&#x221B;&#x221C;&#x221D;&#x2223;&#x2224;&#x2225;&#x2226;&#x2227;&#x2228;&#x2229;&#x222A;',
					'&#x222B;&#x222C;&#x222D;&#x222E;&#x222F;&#x2230;&#x2231;&#x2232;&#x2233;&#x2234;&#x2235;&#x2236;',
					'&#x2237;&#x2238;&#x2239;&#x223A;&#x223B;&#x223C;&#x223D;&#x223E;&#x2240;&#x2241;&#x2242;&#x2243;',
					'&#x2244;&#x2245;&#x2246;&#x2247;&#x2248;&#x2249;&#x224A;&#x224B;&#x224C;&#x224D;&#x224E;&#x224F;',
					'&#x2250;&#x2251;&#x2252;&#x2253;&#x2254;&#x2255;&#x2256;&#x2257;&#x2258;&#x2259;&#x225A;&#x225B;',
					'&#x225C;&#x225D;&#x225E;&#x225F;&#x2260;&#x2261;&#x2262;&#x2263;&#x2264;&#x2265;&#x2266;&#x2267;',
					'&#x2268;&#x2269;&#x226A;&#x226B;&#x226C;&#x226D;&#x226E;&#x226F;&#x2270;&#x2271;&#x2272;&#x2273;',
					'&#x2274;&#x2275;&#x2276;&#x2277;&#x2278;&#x2279;&#x227A;&#x227B;&#x227C;&#x227D;&#x227E;&#x227F;',
					'&#x2280;&#x2281;&#x2282;&#x2283;&#x2284;&#x2285;&#x2286;&#x2287;&#x2288;&#x2289;&#x228A;&#x228B;',
					'&#x228C;&#x228D;&#x228E;&#x228F;&#x2290;&#x2291;&#x2292;&#x2293;&#x2294;&#x2295;&#x2296;&#x2297;',
					'&#x2298;&#x2299;&#x229A;&#x229B;&#x229C;&#x229D;&#x229E;&#x229F;&#x22A0;&#x22A1;&#x22A2;&#x22A3;',
					'&#x22A5;&#x22A6;&#x22A7;&#x22A8;&#x22A9;&#x22AA;&#x22AB;&#x22AC;&#x22AD;&#x22AE;&#x22AF;&#x22B0;',
					'&#x22B1;&#x22B2;&#x22B3;&#x22B4;&#x22B5;&#x22B6;&#x22B7;&#x22B8;&#x22B9;&#x22BA;&#x22BB;&#x22BC;',
					'&#x22BD;&#x22C0;&#x22C1;&#x22C2;&#x22C3;&#x22C4;&#x22C5;&#x22C6;&#x22C7;&#x22C8;&#x22C9;&#x22CA;',
					'&#x22CB;&#x22CC;&#x22CD;&#x22CE;&#x22CF;&#x22D0;&#x22D1;&#x22D2;&#x22D3;&#x22D4;&#x22D5;&#x22D6;',
					'&#x22D7;&#x22D8;&#x22D9;&#x22DA;&#x22DB;&#x22DC;&#x22DD;&#x22DE;&#x22DF;&#x22E0;&#x22E1;&#x22E2;',
					'&#x22E3;&#x22E4;&#x22E5;&#x22E6;&#x22E7;&#x22E8;&#x22E9;&#x22EA;&#x22EB;&#x22EC;&#x22ED;&#x22EE;',
					'&#x22EF;&#x22F0;&#x22F1;&#x22F2;&#x22F3;&#x22F4;&#x22F5;&#x22F6;&#x22F7;&#x22F8;&#x22F9;&#x22FA;',
					'&#x22FB;&#x22FC;&#x22FD;&#x22FE;&#x22FF;&#x2305;&#x2306;&#x2308;&#x2309;&#x230A;&#x230B;&#x231C;',
					'&#x231D;&#x231E;&#x231F;&#x2322;&#x2323;&#x2329;&#x232A;&#x233D;&#x233F;&#x23B0;&#x23B1;&#x25B2;',
					'&#x25B3;&#x25B4;&#x25B5;&#x25B6;&#x25B7;&#x25B8;&#x25B9;&#x25BC;&#x25BD;&#x25BE;&#x25BF;&#x25C0;',
					'&#x25C1;&#x25C2;&#x25C3;&#x25C4;&#x25C5;&#x25CA;&#x25CB;&#x25E6;&#x25EB;&#x25EC;&#x25F8;&#x25F9;',
					'&#x25FA;&#x25FB;&#x25FC;&#x25FD;&#x25FE;&#x25FF;&#x2605;&#x2606;&#x2772;&#x2773;&#x27D1;&#x27D2;',
					'&#x27D3;&#x27D4;&#x27D5;&#x27D6;&#x27D7;&#x27D8;&#x27D9;&#x27DA;&#x27DB;&#x27DC;&#x27DD;&#x27DE;',
					'&#x27DF;&#x27E0;&#x27E1;&#x27E2;&#x27E3;&#x27E4;&#x27E5;&#x27E6;&#x27E7;&#x27E8;&#x27E9;&#x27EA;',
					'&#x27EB;&#x27F0;&#x27F1;&#x27F2;&#x27F3;&#x27F4;&#x27F5;&#x27F6;&#x27F7;&#x27F8;&#x27F9;&#x27FA;',
					'&#x27FB;&#x27FC;&#x27FD;&#x27FE;&#x27FF;&#x2900;&#x2901;&#x2902;&#x2903;&#x2904;&#x2905;&#x2906;',
					'&#x2907;&#x2908;&#x2909;&#x290A;&#x290B;&#x290C;&#x290D;&#x290E;&#x290F;&#x2910;&#x2911;&#x2912;',
					'&#x2913;&#x2914;&#x2915;&#x2916;&#x2917;&#x2918;&#x2919;&#x291A;&#x291B;&#x291C;&#x291D;&#x291E;',
					'&#x291F;&#x2920;&#x2921;&#x2922;&#x2923;&#x2924;&#x2925;&#x2926;&#x2927;&#x2928;&#x2929;&#x292A;',
					'&#x292B;&#x292C;&#x292D;&#x292E;&#x292F;&#x2930;&#x2931;&#x2932;&#x2933;&#x2934;&#x2935;&#x2936;',
					'&#x2937;&#x2938;&#x2939;&#x293A;&#x293B;&#x293C;&#x293D;&#x293E;&#x293F;&#x2940;&#x2941;&#x2942;',
					'&#x2943;&#x2944;&#x2945;&#x2946;&#x2947;&#x2948;&#x2949;&#x294A;&#x294B;&#x294C;&#x294D;&#x294E;',
					'&#x294F;&#x2950;&#x2951;&#x2952;&#x2953;&#x2954;&#x2955;&#x2956;&#x2957;&#x2958;&#x2959;&#x295A;',
					'&#x295B;&#x295C;&#x295D;&#x295E;&#x295F;&#x2960;&#x2961;&#x2962;&#x2963;&#x2964;&#x2965;&#x2966;',
					'&#x2967;&#x2968;&#x2969;&#x296A;&#x296B;&#x296C;&#x296D;&#x296E;&#x296F;&#x2970;&#x2971;&#x2972;',
					'&#x2973;&#x2974;&#x2975;&#x2976;&#x2977;&#x2978;&#x2979;&#x297A;&#x297B;&#x297C;&#x297D;&#x297E;',
					'&#x297F;&#x2980;&#x2982;&#x2983;&#x2984;&#x2985;&#x2986;&#x2987;&#x2988;&#x2989;&#x298A;&#x298B;',
					'&#x298C;&#x298D;&#x298E;&#x298F;&#x2990;&#x2991;&#x2992;&#x2993;&#x2994;&#x2995;&#x2996;&#x2997;',
					'&#x2998;&#x2999;&#x299A;&#x29B6;&#x29B7;&#x29B8;&#x29B9;&#x29C0;&#x29C1;&#x29C4;&#x29C5;&#x29C6;',
					'&#x29C7;&#x29C8;&#x29CE;&#x29CF;&#x29D0;&#x29D1;&#x29D2;&#x29D3;&#x29D4;&#x29D5;&#x29D6;&#x29D7;',
					'&#x29D8;&#x29D9;&#x29DA;&#x29DB;&#x29DF;&#x29E1;&#x29E2;&#x29E3;&#x29E4;&#x29E5;&#x29E6;&#x29EB;',
					'&#x29F4;&#x29F5;&#x29F6;&#x29F7;&#x29F8;&#x29F9;&#x29FA;&#x29FB;&#x29FC;&#x29FD;&#x29FE;&#x29FF;',
					'&#x2A00;&#x2A01;&#x2A02;&#x2A03;&#x2A04;&#x2A05;&#x2A06;&#x2A07;&#x2A08;&#x2A09;&#x2A0A;&#x2A0B;',
					'&#x2A0C;&#x2A0D;&#x2A0E;&#x2A0F;&#x2A10;&#x2A11;&#x2A12;&#x2A13;&#x2A14;&#x2A15;&#x2A16;&#x2A17;',
					'&#x2A18;&#x2A19;&#x2A1A;&#x2A1B;&#x2A1C;&#x2A1D;&#x2A1E;&#x2A1F;&#x2A20;&#x2A21;&#x2A22;&#x2A23;',
					'&#x2A24;&#x2A25;&#x2A26;&#x2A27;&#x2A28;&#x2A29;&#x2A2A;&#x2A2B;&#x2A2C;&#x2A2D;&#x2A2E;&#x2A2F;',
					'&#x2A30;&#x2A31;&#x2A32;&#x2A33;&#x2A34;&#x2A35;&#x2A36;&#x2A37;&#x2A38;&#x2A39;&#x2A3A;&#x2A3B;',
					'&#x2A3C;&#x2A3D;&#x2A3E;&#x2A3F;&#x2A40;&#x2A41;&#x2A42;&#x2A43;&#x2A44;&#x2A45;&#x2A46;&#x2A47;',
					'&#x2A48;&#x2A49;&#x2A4A;&#x2A4B;&#x2A4C;&#x2A4D;&#x2A4E;&#x2A4F;&#x2A50;&#x2A51;&#x2A52;&#x2A53;',
					'&#x2A54;&#x2A55;&#x2A56;&#x2A57;&#x2A58;&#x2A59;&#x2A5A;&#x2A5B;&#x2A5C;&#x2A5D;&#x2A5E;&#x2A5F;',
					'&#x2A60;&#x2A61;&#x2A62;&#x2A63;&#x2A64;&#x2A65;&#x2A66;&#x2A67;&#x2A68;&#x2A69;&#x2A6A;&#x2A6B;',
					'&#x2A6C;&#x2A6D;&#x2A6E;&#x2A6F;&#x2A70;&#x2A71;&#x2A72;&#x2A73;&#x2A74;&#x2A75;&#x2A76;&#x2A77;',
					'&#x2A78;&#x2A79;&#x2A7A;&#x2A7B;&#x2A7C;&#x2A7D;&#x2A7E;&#x2A7F;&#x2A80;&#x2A81;&#x2A82;&#x2A83;',
					'&#x2A84;&#x2A85;&#x2A86;&#x2A87;&#x2A88;&#x2A89;&#x2A8A;&#x2A8B;&#x2A8C;&#x2A8D;&#x2A8E;&#x2A8F;',
					'&#x2A90;&#x2A91;&#x2A92;&#x2A93;&#x2A94;&#x2A95;&#x2A96;&#x2A97;&#x2A98;&#x2A99;&#x2A9A;&#x2A9B;',
					'&#x2A9C;&#x2A9D;&#x2A9E;&#x2A9F;&#x2AA0;&#x2AA1;&#x2AA2;&#x2AA3;&#x2AA4;&#x2AA5;&#x2AA6;&#x2AA7;',
					'&#x2AA8;&#x2AA9;&#x2AAA;&#x2AAB;&#x2AAC;&#x2AAD;&#x2AAE;&#x2AAF;&#x2AB0;&#x2AB1;&#x2AB2;&#x2AB3;',
					'&#x2AB4;&#x2AB5;&#x2AB6;&#x2AB7;&#x2AB8;&#x2AB9;&#x2ABA;&#x2ABB;&#x2ABC;&#x2ABD;&#x2ABE;&#x2ABF;',
					'&#x2AC0;&#x2AC1;&#x2AC2;&#x2AC3;&#x2AC4;&#x2AC5;&#x2AC6;&#x2AC7;&#x2AC8;&#x2AC9;&#x2ACA;&#x2ACB;',
					'&#x2ACC;&#x2ACD;&#x2ACE;&#x2ACF;&#x2AD0;&#x2AD1;&#x2AD2;&#x2AD3;&#x2AD4;&#x2AD5;&#x2AD6;&#x2AD7;',
					'&#x2AD8;&#x2AD9;&#x2ADA;&#x2ADB;&#x2ADC;&#x2ADD;&#x2ADE;&#x2ADF;&#x2AE0;&#x2AE2;&#x2AE3;&#x2AE4;',
					'&#x2AE5;&#x2AE6;&#x2AE7;&#x2AE8;&#x2AE9;&#x2AEA;&#x2AEB;&#x2AEC;&#x2AED;&#x2AEE;&#x2AEF;&#x2AF0;',
					'&#x2AF2;&#x2AF3;&#x2AF4;&#x2AF5;&#x2AF6;&#x2AF7;&#x2AF8;&#x2AF9;&#x2AFA;&#x2AFB;&#x2AFC;&#x2AFD;')" />
					
	<!-- A string of '-'s repeated exactly as many times as the operators above -->
	<xsl:variable name="sMinuses">
		<xsl:call-template name="SRepeatChar">
			<xsl:with-param name="cchRequired" select="string-length($sOperators)" />
			<xsl:with-param name="ch" select="'-'" />
		</xsl:call-template>
	</xsl:variable>
	
	<!-- Every single unicode character that is recognized by OMML as a number -->
	<xsl:variable name="sNumbers" select="'0123456789'"/>
	
	<!-- A string of '0's repeated exactly as many times as the list of numbers above -->
	<xsl:variable name="sZeros">
		<xsl:call-template name="SRepeatChar">
			<xsl:with-param name="cchRequired" select="string-length($sNumbers)" />
			<xsl:with-param name="ch" select="'0'" />
		</xsl:call-template>
	</xsl:variable>	

	<!-- %%Template: SReplace

		Replace all occurences of sOrig in sInput with sReplacement
		and return the resulting string. -->
	<xsl:template name="SReplace">
		<xsl:param name="sInput" />
		<xsl:param name="sOrig" />
		<xsl:param name="sReplacement" />

		<xsl:choose>
			<xsl:when test="not(contains($sInput, $sOrig))">
				<xsl:value-of select="$sInput" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="sBefore" select="substring-before($sInput, $sOrig)" />
				<xsl:variable name="sAfter" select="substring-after($sInput, $sOrig)" />
				<xsl:variable name="sAfterProcessed">
					<xsl:call-template name="SReplace">
						<xsl:with-param name="sInput" select="$sAfter" />
						<xsl:with-param name="sOrig" select="$sOrig" />
						<xsl:with-param name="sReplacement" select="$sReplacement" />
					</xsl:call-template>
				</xsl:variable>

				<xsl:value-of select="concat($sBefore, concat($sReplacement, $sAfterProcessed))" /> 
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>	
		
	<!-- Templates -->					
	<xsl:template match="/">
		<mml:math>
			<xsl:apply-templates select="*" />
		</mml:math>
	</xsl:template>
	
	<xsl:template match="*">
		<xsl:apply-templates select="*" />
	</xsl:template>
	
	<xsl:template match="m:acc"> 
		<mml:mover>
			<xsl:attribute name="accent">true</xsl:attribute>
			<xsl:apply-templates select="m:e[1]" /> 
			<mml:mtext>
				<xsl:call-template name="CreateTokenAttributes">
					<xsl:with-param name="scr" select="m:e[1]/*/m:rPr[last()]/m:scr/@m:val" />
					<xsl:with-param name="sty" select="m:e[1]/*/m:rPr[last()]/m:sty/@m:val" />
					<xsl:with-param name="nor" select="m:e[1]/*/m:rPr[last()]/m:nor/@m:val" />
				</xsl:call-template>
				<xsl:choose>
					<xsl:when test="not(m:accPr[last()]/m:chr)">
						<xsl:value-of select="'&#x0302;'" /> 
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="substring(m:accPr/m:chr/@m:val,1,1)" />
					</xsl:otherwise>
				</xsl:choose>
			</mml:mtext>
		</mml:mover>
	</xsl:template>
	
	<xsl:template match="m:sPre">
		<mml:mmultiscripts>
			<mml:mrow>
				<xsl:apply-templates select="m:e[1]" />
			</mml:mrow>
			<mml:mprescripts />
			<mml:mrow>
				<xsl:apply-templates select="m:sub[1]" />
			</mml:mrow>
			<mml:mrow>
				<xsl:apply-templates select="m:sup[1]" />
			</mml:mrow>
		</mml:mmultiscripts>
	</xsl:template>
	
	<xsl:template match="m:m">
		<mml:mtable>
			<xsl:call-template name="CreateMathMLMatrixAttr">
				<xsl:with-param name="mcJc" select="m:mPr[last()]/m:mcs/m:mc/m:mcPr[last()]/m:mcJc/@m:val" />
			</xsl:call-template>
			<xsl:for-each select="m:mr">
				<mml:mtr>
					<xsl:for-each select="m:e">
						<mml:mtd>
							<xsl:apply-templates select="." />
						</mml:mtd>
					</xsl:for-each>
				</mml:mtr>
			</xsl:for-each>
		</mml:mtable>
	</xsl:template>
	
	<xsl:template name="CreateMathMLMatrixAttr">
		<xsl:param name="mcJc" />
		<xsl:variable name="sLowerCaseMcjc" select="translate($mcJc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                             'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:choose>
			<xsl:when test="$sLowerCaseMcjc='left'">
				<xsl:attribute name="columnalign">left</xsl:attribute>
			</xsl:when>
			<xsl:when test="$sLowerCaseMcjc='right'">
				<xsl:attribute name="columnalign">right</xsl:attribute>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="m:phant">
		<xsl:variable name="sLowerCaseWidth" select="translate(m:phantPr[last()]/m:width/@m:val, 
		                                                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                       'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:variable name="sLowerCaseAsc" select="translate(m:phantPr[last()]/m:asc/@m:val, 
		                                                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                     'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:variable name="sLowerCaseDec" select="translate(m:phantPr[last()]/m:dec/@m:val, 
		                                                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                     'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:if test="not($sLowerCaseWidth='off' and 
	                      $sLowerCaseAsc='off'   and
	                      $sLowerCaseDec='off')">
			<mml:phantom>				
				<xsl:apply-templates select="m:e[1]" />
			</mml:phantom>
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="m:rad"> 
		<xsl:variable name="sLowerCaseDegHide" select="translate(m:radPr[last()]/m:degHide/@m:val, 
		                                                          'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                          'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:choose>
			<xsl:when test="$sLowerCaseDegHide='on'"> 
				<mml:msqrt>
					<xsl:apply-templates select="m:e[1]" />
				</mml:msqrt>
			</xsl:when>
			<xsl:otherwise> 
				<mml:mroot>
					<mml:mrow>
						<xsl:apply-templates select="m:e[1]" />
					</mml:mrow>
					<mml:mrow>
						<xsl:apply-templates select="m:deg[1]" />
					</mml:mrow>
				</mml:mroot>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<!-- %%Template match m:nary 
		Process an n-ary. 
		
		Decides, based on which arguments are supplied, between
		using an mo, msup, msub, or msubsup for the n-ary operator		
	-->
	<xsl:template match="m:nary">
		<xsl:variable name="sLowerCaseSubHide">
			<xsl:choose>
				<xsl:when test="count(m:naryPr[last()]/m:subHide) = 0">
					<xsl:text>off</xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="translate(m:naryPr[last()]/m:subHide/@m:val, 
	                                  'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
	                                  'abcdefghijklmnopqrstuvwxyz')" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="sLowerCaseSupHide">
			<xsl:choose>
				<xsl:when test="count(m:naryPr[last()]/m:supHide) = 0">
					<xsl:text>off</xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="translate(m:naryPr[last()]/m:supHide/@m:val, 
	                                  'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
	                                  'abcdefghijklmnopqrstuvwxyz')" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="not($sLowerCaseSupHide='off') and 
			                not($sLowerCaseSubHide='off')">
				<mml:mo>
					<xsl:choose>
						<xsl:when test="not(m:naryPr[last()]/m:chr/@m:val) or
			                            m:naryPr[last()]/m:chr/@m:val=''">
							<xsl:text disable-output-escaping="yes">&amp;#x222b;</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="m:naryPr[last()]/m:chr/@m:val" />
						</xsl:otherwise>
					</xsl:choose>
				</mml:mo>
			</xsl:when>
			<xsl:when test="not($sLowerCaseSubHide='off')">
				<xsl:choose>
					<xsl:when test="m:naryPr[last()]/m:limLoc/@m:val='subSup'">
						<mml:msup>
							<mml:mo>
								<xsl:choose>
									<xsl:when test="not(m:naryPr[last()]/m:chr/@m:val) or
			                                        m:naryPr[last()]/m:chr/@m:val=''">
										<xsl:text disable-output-escaping="yes">&amp;#x222b;</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="m:naryPr[last()]/m:chr/@m:val" />
									</xsl:otherwise>
								</xsl:choose>
							</mml:mo>
							<xsl:apply-templates select="m:sup[1]" />
						</mml:msup>
					</xsl:when>
					<xsl:otherwise>
						<mml:mover>
							<mml:mo>
								<xsl:choose>
									<xsl:when test="not(m:naryPr[last()]/m:chr/@m:val) or
			                                        m:naryPr[last()]/m:chr/@m:val=''">
										<xsl:text disable-output-escaping="yes">&amp;#x222b;</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="m:naryPr[last()]/m:chr/@m:val" />
									</xsl:otherwise>
								</xsl:choose>
							</mml:mo>
							<xsl:apply-templates select="m:sup[1]" />
						</mml:mover>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="not($sLowerCaseSupHide='off')">
				<xsl:choose>
					<xsl:when test="m:naryPr[last()]/m:limLoc/@m:val='subSup'">
						<mml:msub>
							<mml:mo>
								<xsl:choose>
									<xsl:when test="not(m:naryPr[last()]/m:chr/@m:val) or
			                                        m:naryPr[last()]/m:chr/@m:val=''">
										<xsl:text disable-output-escaping="yes">&amp;#x222b;</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="m:naryPr[last()]/m:chr/@m:val" />
									</xsl:otherwise>
								</xsl:choose>
							</mml:mo>
							<xsl:apply-templates select="m:sub[1]" />
						</mml:msub>
					</xsl:when>
					<xsl:otherwise>
						<mml:munder>
							<mml:mo>
								<xsl:choose>
									<xsl:when test="not(m:naryPr[last()]/m:chr/@m:val) or
			                        m:naryPr[last()]/m:chr/@m:val=''">
										<xsl:text disable-output-escaping="yes">&amp;#x222b;</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="m:naryPr[last()]/m:chr/@m:val" />
									</xsl:otherwise>
								</xsl:choose>
							</mml:mo>
							<xsl:apply-templates select="m:sub[1]" />
						</mml:munder>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="m:naryPr[last()]/m:limLoc/@m:val='subSup'">
						<mml:msubsup>
							<mml:mo>
								<xsl:choose>
									<xsl:when test="not(m:naryPr[last()]/m:chr/@m:val) or
			                                        m:naryPr[last()]/m:chr/@m:val=''">
										<xsl:text disable-output-escaping="yes">&amp;#x222b;</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="m:naryPr[last()]/m:chr/@m:val" />
									</xsl:otherwise>
								</xsl:choose>
							</mml:mo>
							<xsl:apply-templates select="m:sub[1]" />
							<xsl:apply-templates select="m:sup[1]" />
						</mml:msubsup>
					</xsl:when>
					<xsl:otherwise>
						<mml:munderover>
							<mml:mo>
								<xsl:choose>
									<xsl:when test="not(m:naryPr[last()]/m:chr/@m:val) or
			                                        m:naryPr[last()]/m:chr/@m:val=''">
										<xsl:text disable-output-escaping="yes">&amp;#x222b;</xsl:text>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="m:naryPr[last()]/m:chr/@m:val" />
									</xsl:otherwise>
								</xsl:choose>
							</mml:mo>
							<xsl:apply-templates select="m:sub[1]" />
							<xsl:apply-templates select="m:sup[1]" />
						</mml:munderover>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
		<mml:mrow>
			<xsl:apply-templates select="m:e[1]" />
		</mml:mrow>
	</xsl:template>
	
	<xsl:template match="m:limLow">
		<mml:munder>
			<mml:mrow>
				<xsl:apply-templates select="m:e[1]" />
			</mml:mrow>
			<mml:mrow>
				<xsl:apply-templates select="m:lim[1]" />
			</mml:mrow>
		</mml:munder>
	</xsl:template>
	
	<xsl:template match="m:limUpp">
		<mml:mover>
			<mml:mrow>
				<xsl:apply-templates select="m:e[1]" />
			</mml:mrow>
			<mml:mrow>
				<xsl:apply-templates select="m:lim[1]" />
			</mml:mrow>
		</mml:mover>
	</xsl:template>
	
	<xsl:template match="m:sSub">
		<mml:msub>
			<mml:mrow>
				<xsl:apply-templates select="m:e[1]" />
			</mml:mrow>
			<mml:mrow>
				<xsl:apply-templates select="m:sub[1]" />
			</mml:mrow>
		</mml:msub>		
	</xsl:template>
	
	<xsl:template match="m:sSup">
		<mml:msup>
			<mml:mrow>
				<xsl:apply-templates select="m:e[1]" />
			</mml:mrow>
			<mml:mrow>
				<xsl:apply-templates select="m:sup[1]" />
			</mml:mrow>
		</mml:msup>		
	</xsl:template>
	
	<xsl:template match="m:sSubSup">
		<mml:msubsup>
			<mml:mrow>
				<xsl:apply-templates select="m:e[1]" />
			</mml:mrow>
			<mml:mrow>
				<xsl:apply-templates select="m:sub[1]" />
			</mml:mrow>
			<mml:mrow>
				<xsl:apply-templates select="m:sup[1]" />
			</mml:mrow>
		</mml:msubsup>
	</xsl:template>
			
	<xsl:template match="m:groupChr">
		<xsl:variable name="sLowerCaseOpEmu" select="translate(m:groupChrPr[last()]/m:opEmu/@m:val, 
		                                                   'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                   'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:choose>
			<xsl:when test="$sLowerCaseOpEmu='on'">
				<mml:mrow>
					<xsl:call-template name="CreateGroupChr" />
				</mml:mrow>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="CreateGroupChr" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="CreateGroupChr">
		<xsl:variable name="sLowerCasePos" select="translate(m:groupChrPr[last()]/m:pos/@m:val, 
		                                                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                     'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:choose>
			<xsl:when test="$sLowerCasePos!='top' or 
	                    not(m:groupChrPr[last()]/m:pos/@m:val)   or
	                    m:groupChrPr[last()]/m:pos/@m:val=''">
				<mml:munder>
					<xsl:apply-templates select="m:e[1]" />
					<mml:mo>
						<xsl:choose>
							<xsl:when test="string-length(m:groupChrPr[last()]/m:chr/@m:val) &gt;= 1">
								<xsl:value-of select="substring(m:groupChrPr[last()]/m:chr/@m:val,1,1)" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:text disable-output-escaping="yes">&amp;#x023DF;</xsl:text>
							</xsl:otherwise>
						</xsl:choose>
					</mml:mo>
				</mml:munder>
			</xsl:when>
			<xsl:otherwise>
				<mml:mover>
					<xsl:apply-templates select="m:e[1]" />
					<mml:mo>
						<xsl:choose>
							<xsl:when test="string-length(m:groupChrPr[last()]/m:chr/@m:val) &gt;= 1">
								<xsl:value-of select="substring(m:groupChrPr[last()]/m:chr/@m:val,1,1)" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:text disable-output-escaping="yes">&amp;#x023DF;</xsl:text>
							</xsl:otherwise>
						</xsl:choose>
					</mml:mo>
				</mml:mover>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="fName">
		<xsl:for-each select="m:fName/*">
			<xsl:apply-templates select="." />
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template match="m:func">
		<mml:mrow>
			<mml:mrow>
				<xsl:call-template name="fName" />
			</mml:mrow>
			<mml:mo>&#x02061;</mml:mo>
			<xsl:apply-templates select="*[position() &gt; 1]" />
		</mml:mrow>
	</xsl:template>

	<!-- %%Template: match m:f 
		
		m:f maps directly to mfrac. 
	-->
	<xsl:template match="m:f">
		<mml:mfrac>
			<xsl:call-template name="CreateMathMLFracProp">
				<xsl:with-param name="type" select="m:fPr[last()]/m:type/@m:val" />
				<xsl:with-param name="baseJc" select="m:fPr[last()]/m:baseJc/@m:val" />
				<xsl:with-param name="numJc" select="m:fPr[last()]/m:numJc/@m:val" />
				<xsl:with-param name="denJc" select="m:fPr[last()]/m:type/@m:val" />
			</xsl:call-template>
			
			<mml:mrow><xsl:apply-templates select="m:num[1]" /></mml:mrow>
			<mml:mrow><xsl:apply-templates select="m:den[1]" /></mml:mrow>
		</mml:mfrac>
	</xsl:template>


	<!-- %%Template: CreateMathMLFracProp 
		
			Make fraction properties based on supplied parameters.
			OMML differentiates between a linear fraction and a skewed
			one. For MathML, we write both as bevelled.
	-->
	<xsl:template name="CreateMathMLFracProp">
		<xsl:param name="type" />
		<xsl:param name="baseJc" />
		<xsl:param name="numJc" />
		<xsl:param name="denJc" />
		<xsl:variable name="sLowerCaseType" select="translate($type, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:variable name="sLowerCaseNumJc" select="translate($numJc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:variable name="sLowerCaseDenJc" select="translate($denJc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')" />
		
		<xsl:if test="$sLowerCaseType='skw' or $sLowerCaseType='lin'">
			<xsl:attribute name="bevelled">true</xsl:attribute>
		</xsl:if>
		<xsl:if test="$sLowerCaseType='nobar'">
			<xsl:attribute name="linethickness">0pt</xsl:attribute>
		</xsl:if>
		<xsl:choose>
			<xsl:when test="sLowerCaseNumJc='right'">
				<xsl:attribute name="numalign">right</xsl:attribute>
			</xsl:when>
			<xsl:when test="sLowerCaseNumJc='left'">
				<xsl:attribute name="numalign">left</xsl:attribute>
			</xsl:when>
		</xsl:choose>
		<xsl:choose>
			<xsl:when test="sLowerCaseDenJc='right'">
				<xsl:attribute name="numalign">right</xsl:attribute>
			</xsl:when>
			<xsl:when test="sLowerCaseDenJc='left'">
				<xsl:attribute name="numalign">left</xsl:attribute>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- %%Template: match m:e | m:den | m:num | m:lim | m:sup | m:sub 
		
		These element delinate parts of an equation (like the numerator).  -->
	<xsl:template match="m:e | m:den | m:num | m:lim | m:sup | m:sub">
		<xsl:choose>

			<!-- If there is no scriptLevel speified, just call through -->
			<xsl:when test="not(m:argPr[last()]/m:scrLvl/@m:val)">
				<xsl:apply-templates select="*" />
			</xsl:when>

			<!-- Otherwise, create an mstyle and set the script level -->
			<xsl:otherwise>
				<mml:mstyle>
					<xsl:attribute name="scriptlevel">
						<xsl:value-of select="m:argPr[last()]/m:scrLvl/@m:val" />
					</xsl:attribute>
					<xsl:apply-templates select="*" />
				</mml:mstyle>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
		
	<xsl:template match="m:bar">
		<xsl:variable name="sLowerCasePos" select="translate(m:barPr/m:pos/@m:val, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                                       'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:choose>
			<xsl:when test="$sLowerCasePos!='bot' or 
	                        not($sLowerCasePos)   or
	                        $sLowerCasePos=''   ">
				<mml:mover>
					<xsl:attribute name="accent">true</xsl:attribute>
					<xsl:apply-templates select="m:e[1]" />
					<mml:mo>
						<xsl:text disable-output-escaping="yes">&amp;#x000AF;</xsl:text>
					</mml:mo>
				</mml:mover>
			</xsl:when>
			<xsl:otherwise>
				<mml:munder>
					<xsl:apply-templates select="m:e[1]" />
					<mml:mo>
						<xsl:text disable-output-escaping="yes">&amp;#x00332;</xsl:text>
					</mml:mo>
				</mml:munder>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<!-- %%Template match m:d

		Process a delimiter. 
	-->
	<xsl:template match="m:d">
		<mml:mfenced>		
			<!-- open: default is ( for both OMML and MathML -->
			<xsl:if test="m:dPr[1]/m:begChr/@m:val and not(m:dPr[1]/m:begChr/@m:val ='(')">
				<xsl:attribute name="open">
					<xsl:value-of select="m:dPr[1]/m:begChr/@m:val" />
				</xsl:attribute>
			</xsl:if>
			
			<!-- close: default is ) for both OMML and MathML -->
			<xsl:if test="m:dPr[1]/m:endChr/@m:val and not(m:dPr[1]/m:endChr/@m:val =')')">
				<xsl:attribute name="close">
					<xsl:value-of select="m:dPr[1]/m:endChr/@m:val" />
				</xsl:attribute>
			</xsl:if>
			
			<!-- separator: the default is ',' for MathML, and '|' for OMML -->
			<xsl:choose>
				<!-- Matches MathML default. Write nothing -->
				<xsl:when test="m:dPr[1]/m:sepChr/@m:val = ','" />

				<!-- OMML default: | -->
				<xsl:when test="not(m:dPr[1]/m:sepChr/@m:val)">
					<xsl:attribute name="separators">
						<xsl:value-of select="'|'" />
					</xsl:attribute>
				</xsl:when>

				<xsl:otherwise>
					<xsl:attribute name="separators">
						<xsl:value-of select="m:dPr[1]/m:sepChr/@m:val" />
					</xsl:attribute>			
				</xsl:otherwise>						
			</xsl:choose>
			
			<!-- now write all the children. Put each one into an mrow
			just in case it produces multiple runs, etc -->
			<xsl:for-each select="m:e">
				<mml:mrow>
					<xsl:apply-templates select="." />
				</mml:mrow>
			</xsl:for-each>			
		</mml:mfenced>
	</xsl:template>
	
	<xsl:template match="m:r">
		<xsl:variable name="sLowerCaseNor" select="translate(child::m:rPr[last()]/m:nor/@m:val, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                                       'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:choose>
			<xsl:when test="$sLowerCaseNor='on'">
				<mml:mtext>
					<xsl:value-of select=".//m:t" />
				</mml:mtext>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select=".//m:t">
					<xsl:call-template name="ParseMt">
						<xsl:with-param name="sToParse" select="text()" />
						<xsl:with-param name="scr" select="../m:rPr[last()]/m:scr/@m:val" />
						<xsl:with-param name="sty" select="../m:rPr[last()]/m:sty/@m:val" />
						<xsl:with-param name="nor" select="../m:rPr[last()]/m:nor/@m:val" />
					</xsl:call-template>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="CreateTokenAttributes">
		<xsl:param name="scr" />
		<xsl:param name="sty" />
		<xsl:param name="nor" />
		<xsl:param name="nCharToPrint" />
		<xsl:param name="sTokenType" />
		<xsl:variable name="sLowerCaseNor" select="translate($nor, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 
		                                                           'abcdefghijklmnopqrstuvwxyz')" />
		<xsl:choose>
			<xsl:when test="$sLowerCaseNor = 'on'">
				<xsl:attribute name="mathvariant">normal</xsl:attribute>
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="mathvariant">
					<xsl:choose>
						<!-- numbers don't care -->
						<xsl:when test="$sTokenType='mn'" />

						<xsl:when test="$scr='monospace'">monospace</xsl:when>
						<xsl:when test="$scr='sans-serif' and $sty='i'">sans-serif-italic</xsl:when>
						<xsl:when test="$scr='sans-serif' and $sty='b'">bold-sans-serif</xsl:when>
						<xsl:when test="$scr='sans-serif'">sans-serif</xsl:when>
						<xsl:when test="$scr='fraktur' and $sty='b'">bold-fraktur</xsl:when>
						<xsl:when test="$scr='fraktur'">fraktur</xsl:when>
						<xsl:when test="$scr='double-struck'">double-struck</xsl:when>
						<xsl:when test="$scr='script' and $sty='b'">bold-script</xsl:when>
						<xsl:when test="$scr='script'">script</xsl:when>
						<xsl:when test="($scr='roman' or not($scr) or $scr='') and $sty='b'">bold</xsl:when>
						<xsl:when test="($scr='roman' or not($scr) or $scr='') and $sty='i'">italic</xsl:when>
						<xsl:when test="($scr='roman' or not($scr) or $scr='') and $sty='p'">normal</xsl:when>
						
						<xsl:otherwise />
					</xsl:choose>
				</xsl:variable>
				<xsl:variable name="fontweight">
					<xsl:choose>
						<xsl:when test="$sty='b' or $sty='bi'">bold</xsl:when>
						<xsl:otherwise>normal</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				<xsl:variable name="fontstyle">
					<xsl:choose>
						<xsl:when test="$sty='p' or $sty='b'">normal</xsl:when>
						<xsl:otherwise>italic</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>

				<!-- Writing of attributes begins here -->
				<xsl:choose>
					<!-- Don't write mathvariant for operators unless they want to be normal -->
					<xsl:when test="$sTokenType='mo' and $mathvariant!='normal'" />

					<!-- A single character within an mi is already italics, don't write -->
					<xsl:when test="$sTokenType='mi' and $nCharToPrint=1 and ($mathvariant='' or $mathvariant='italic')" />
					
					<xsl:when test="$sTokenType='mi' and $nCharToPrint &gt; 1 and ($mathvariant='' or $mathvariant='italic')">
						<xsl:attribute name="mathvariant">
							<xsl:value-of select="'italic'" />
						</xsl:attribute>
					</xsl:when>
					<xsl:when test="$mathvariant!='italic' and $mathvariant!=''">
						<xsl:attribute name="mathvariant">
							<xsl:value-of select="$mathvariant" />
						</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:if test="not($sTokenType='mi' and $nCharToPrint=1) and $fontstyle='italic'">
							<xsl:attribute name="fontstyle">italic</xsl:attribute>
						</xsl:if>
						<xsl:if test="$fontweight='bold'">
							<xsl:attribute name="fontweight">bold</xsl:attribute>
						</xsl:if>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="m:eqArr">
		<mml:mtable>
			<xsl:attribute name="frame">none</xsl:attribute>
			<xsl:attribute name="columnlines">none</xsl:attribute>
			<xsl:attribute name="rowlines">none</xsl:attribute>
			<xsl:for-each select="m:e">
				<mml:mtr>
					<mml:mtd>
						<mml:maligngroup />
						<xsl:choose>
							<xsl:when test="m:argPr[last()]/m:scrLvl/@m:val!='0' or 
					            not(m:argPr[last()]/m:scrLvl/@m:val)  or 
					            m:argPr[last()]/m:scrLvl/@m:val=''">
								<mml:mrow>
									<xsl:call-template name="CreateEqArrRow">
										<xsl:with-param name="align" select="1" />
										<xsl:with-param name="ndCur" select="*[1]" />
									</xsl:call-template>
								</mml:mrow>
							</xsl:when>
							<xsl:otherwise>
								<mml:mstyle>
									<xsl:attribute name="scriptlevel">
										<xsl:value-of select="m:argPr[last()]/m:scrLvl/@m:val" />
									</xsl:attribute>
									<xsl:call-template name="CreateEqArrRow">
										<xsl:with-param name="align" select="1" />
										<xsl:with-param name="ndCur" select="*[1]" />
									</xsl:call-template>
								</mml:mstyle>
							</xsl:otherwise>
						</xsl:choose>
					</mml:mtd>
				</mml:mtr>
			</xsl:for-each>
		</mml:mtable>
	</xsl:template>
	
	<xsl:template name="CreateEqArrRow">
		<xsl:param name="align" /> 
		<xsl:param name="ndCur" />
		<xsl:variable name="sAllMt"> 
			<xsl:for-each select="$ndCur/m:t">
				<xsl:value-of select="." />
			</xsl:for-each>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="local-name($ndCur)='r' and
			                namespace-uri($ndCur)='http://schemas.microsoft.com/office/omml/2004/12/core'">

				<xsl:call-template name="ParseEqArrMr">
					<xsl:with-param name="sToParse" select="$sAllMt" />
					<xsl:with-param name="scr" select="../m:rPr[last()]/m:scr/@m:val" />
					<xsl:with-param name="sty" select="../m:rPr[last()]/m:sty/@m:val" />
					<xsl:with-param name="nor" select="../m:rPr[last()]/m:nor/@m:val" />
					<xsl:with-param name="align" select="$align" />
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates select="$ndCur" />
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="count($ndCur/following-sibling::*) &gt; 0">
			<xsl:variable name="cAmp">
				<xsl:call-template name="CountAmp">
					<xsl:with-param name="sAllMt" select="$sAllMt" />
					<xsl:with-param name="cAmp" select="0" />
				</xsl:call-template>
			</xsl:variable>
			<xsl:call-template name="CreateEqArrRow">
				<xsl:with-param name="align" select="($align+($cAmp mod 2)) mod 2" />
				<xsl:with-param name="ndCur" select="$ndCur/following-sibling::*[1]" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="CountAmp">
		<xsl:param name="sAllMt" />
		<xsl:param name="cAmp" />
		<xsl:choose>
			<xsl:when test="string-length(substring-after($sAllMt, '&amp;')) &gt; 0 or 
			                substring($sAllMt, string-length($sAllMt))='&#x0026;'">
				<xsl:call-template name="CountAmp">
					<xsl:with-param name="sAllMt" select="substring-after($sAllMt, '&#x0026;')" />
					<xsl:with-param name="cAmp" select="$cAmp+1" />
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$cAmp" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<!-- %%Template: ParseEqArrMr
			
			Similar to ParseMt, but this one has to do more for an equation 
			array. The presence of &amp; in a run that is in an equation array
			indicates alignment 
	-->
	<xsl:template name="ParseEqArrMr">
		<xsl:param name="sToParse" />
		<xsl:param name="sty" />
		<xsl:param name="scr" />
		<xsl:param name="nor" />
		<xsl:param name="align" />
		<xsl:if test="string-length($sToParse) &gt; 0"> 
			<xsl:choose>
				<xsl:when test="substring($sToParse,1,1) = '&amp;'">
					<xsl:choose>
						<xsl:when test="$align='0'">
							<mml:maligngroup />
						</xsl:when>
						<xsl:when test="$align='1'">
							<mml:malignmark>
								<xsl:attribute name="edge">left</xsl:attribute>
							</mml:malignmark>
						</xsl:when>
					</xsl:choose>
					<xsl:call-template name="ParseEqArrMr">
						<xsl:with-param name="sToParse" select="substring($sToParse,2)" />
						<xsl:with-param name="scr" select="$scr" />
						<xsl:with-param name="sty" select="$sty" />
						<xsl:with-param name="nor" select="$nor" />
						<xsl:with-param name="align">
							<xsl:choose>
								<xsl:when test="$align='1'">0</xsl:when>
								<xsl:otherwise>1</xsl:otherwise>
							</xsl:choose>
						</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise> 
					<xsl:variable name="sRepNumWith0">
						<xsl:call-template name="SReplaceNumWithZero">
							<xsl:with-param name="sToParse" select="$sToParse" />
						</xsl:call-template>
					</xsl:variable>					
					<xsl:variable name="sRepOperWith-">
						<xsl:call-template name="SReplaceOperWithMinus">
							<xsl:with-param name="sToParse" select="$sRepNumWith0" />
						</xsl:call-template>
					</xsl:variable>
					
					<xsl:variable name="iFirstOper" select="string-length($sRepOperWith-) - string-length(substring-after($sRepOperWith-, '-'))" />
					<xsl:variable name="iFirstNum" select="string-length($sRepOperWith-) - string-length(substring-after($sRepOperWith-, '0'))" />
					<xsl:variable name="iFirstAmp" select="string-length($sRepOperWith-) - string-length(substring-after($sRepOperWith-, '&#x0026;'))" />
					<xsl:variable name="fNumAtPos1"> 
						<xsl:choose>
							<xsl:when test="substring($sRepOperWith-,1,1)='0'">1</xsl:when>
							<xsl:otherwise>0</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<xsl:variable name="fOperAtPos1"> 
						<xsl:choose>
							<xsl:when test="substring($sRepOperWith-,1,1)='-'">1</xsl:when>
							<xsl:otherwise>0</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<xsl:choose>
					
						<!-- Case I: The string begins with neither a number, nor an operator -->
						<xsl:when test="$fNumAtPos1='0' and $fOperAtPos1='0'">
							<xsl:variable name="nCharToPrint">
								<xsl:choose>
									<xsl:when test="($iFirstOper=$iFirstNum) and 
								                ($iFirstAmp=$iFirstOper) and
												($iFirstOper=string-length($sToParse)) and
												$fNumAtPos1='0' and
												$fOperAtPos1='0'">
										<xsl:value-of select="string-length($sToParse)" />
									</xsl:when>
									<xsl:when test="($iFirstOper &lt; $iFirstNum) and 
								                ($iFirstOper &lt; $iFirstAmp)">
										<xsl:value-of select="$iFirstOper - 1" />
									</xsl:when>
									<xsl:when test="($iFirstNum &lt; $iFirstOper) and 
								                ($iFirstNum &lt; $iFirstAmp)">
										<xsl:value-of select="$iFirstNum - 1" />
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="$iFirstAmp - 1" />
									</xsl:otherwise>
								</xsl:choose>
							</xsl:variable>
							<mml:mi>
								<xsl:call-template name="CreateTokenAttributes">
									<xsl:with-param name="scr" select="$scr" />
									<xsl:with-param name="sty" select="$sty" />
									<xsl:with-param name="nor" select="$nor" />
									<xsl:with-param name="nCharToPrint" select="$nCharToPrint" />
									<xsl:with-param name="sTokenType" select="'mi'" />
								</xsl:call-template>
								<xsl:value-of select="substring($sToParse,1,$nCharToPrint)" />
							</mml:mi>
							<xsl:call-template name="ParseEqArrMr">
								<xsl:with-param name="sToParse" select="substring($sToParse, $nCharToPrint+1)" />
								<xsl:with-param name="scr" select="$scr" />
								<xsl:with-param name="sty" select="$sty" />
								<xsl:with-param name="nor" select="$nor" />
								<xsl:with-param name="align" select="$align" />
							</xsl:call-template>
						</xsl:when>
						
						<!-- Case II: There is an operator at position 1 -->
						<xsl:when test="$fOperAtPos1='1'">
							<mml:mo>
								<xsl:call-template name="CreateTokenAttributes">
									<xsl:with-param name="scr" />
									<xsl:with-param name="sty" />
									<xsl:with-param name="nor" select="$nor" />
									<xsl:with-param name="sTokenType" select="'mo'" />
								</xsl:call-template>
								<xsl:value-of select="substring($sToParse,1,1)" />
							</mml:mo>
							<xsl:call-template name="ParseEqArrMr">
								<xsl:with-param name="sToParse" select="substring($sToParse, 2)" />
								<xsl:with-param name="scr" select="$scr" />
								<xsl:with-param name="sty" select="$sty" />
								<xsl:with-param name="nor" select="$nor" />
								<xsl:with-param name="align" select="$align" />
							</xsl:call-template>
						</xsl:when>
						
						<!-- Case III: There is a number at position 1 -->
						<xsl:otherwise>
							<xsl:variable name="sConsecNum">
								<xsl:call-template name="SNumStart">
									<xsl:with-param name="sToParse" select="$sToParse" />
									<xsl:with-param name="sPattern" select="$sRepNumWith0" />									
								</xsl:call-template>
							</xsl:variable>
							<mml:mn>
								<xsl:call-template name="CreateTokenAttributes">
									<xsl:with-param name="scr" />
									<xsl:with-param name="sty" />
									<xsl:with-param name="nor" select="$nor" />
									<xsl:with-param name="sTokenType" select="'mn'" />
								</xsl:call-template>
								<xsl:value-of select="$sConsecNum" />
							</mml:mn>
							<xsl:call-template name="ParseEqArrMr">
								<xsl:with-param name="sToParse" select="substring-after($sToParse, $sConsecNum)" />
								<xsl:with-param name="scr" select="$scr" />
								<xsl:with-param name="sty" select="$sty" />
								<xsl:with-param name="nor" select="$nor" />
								<xsl:with-param name="align" select="$align" />
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:template>
	
	<!-- %%Template: ParseMt

			Produce a run of text. Technically, OMML makes no distinction 
			between numbers, operators, and other characters in a run. For 
			MathML we need to break these into mi, mn, or mo elements. 
			
			See also ParseEqArrMr
	-->	
	<xsl:template name="ParseMt">
		<xsl:param name="sToParse" />
		<xsl:param name="sty" />
		<xsl:param name="scr" />
		<xsl:param name="nor" />
		<xsl:if test="string-length($sToParse) &gt; 0">		
			<xsl:variable name="sRepNumWith0">
				<xsl:call-template name="SReplaceNumWithZero">
					<xsl:with-param name="sToParse" select="$sToParse" />
				</xsl:call-template>
			</xsl:variable>
			<xsl:variable name="sRepOperWith-">
				<xsl:call-template name="SReplaceOperWithMinus">
					<xsl:with-param name="sToParse" select="$sRepNumWith0" />
				</xsl:call-template>
			</xsl:variable>
			
			<xsl:variable name="iFirstOper" select="string-length($sRepOperWith-) - string-length(substring-after($sRepOperWith-, '-'))" />
			<xsl:variable name="iFirstNum" select="string-length($sRepOperWith-) - string-length(substring-after($sRepOperWith-, '0'))" />
			<xsl:variable name="fNumAtPos1">
				<xsl:choose>
					<xsl:when test="substring($sRepOperWith-,1,1)='0'">1</xsl:when>
					<xsl:otherwise>0</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			<xsl:variable name="fOperAtPos1"> 
				<xsl:choose>
					<xsl:when test="substring($sRepOperWith-,1,1)='-'">1</xsl:when>
					<xsl:otherwise>0</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			
			<xsl:choose>
				
				<!-- Case I: The string begins with neither a number, nor an operator -->
				<xsl:when test="$fOperAtPos1='0' and $fNumAtPos1='0'">
					<xsl:variable name="nCharToPrint">
						<xsl:choose>
							<xsl:when test="($iFirstOper=$iFirstNum) and 
											($iFirstOper=string-length($sToParse)) and
							                (substring($sRepOperWith-, string-length($sRepOperWith-))!='0') and 
							                (substring($sRepOperWith-, string-length($sRepOperWith-))!='-')">
								<xsl:value-of select="string-length($sToParse)" />
							</xsl:when>
							<xsl:when test="$iFirstOper &lt; $iFirstNum">
								<xsl:value-of select="$iFirstOper - 1" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$iFirstNum - 1" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<mml:mi>
						<xsl:call-template name="CreateTokenAttributes">
							<xsl:with-param name="scr" select="$scr" />
							<xsl:with-param name="sty" select="$sty" />
							<xsl:with-param name="nor" select="$nor" />
							<xsl:with-param name="nCharToPrint" select="$nCharToPrint" />
							<xsl:with-param name="sTokenType" select="'mi'" />
						</xsl:call-template>
						<xsl:value-of select="substring($sToParse,1,$nCharToPrint)" />
					</mml:mi>
					<xsl:call-template name="ParseMt">
						<xsl:with-param name="sToParse" select="substring($sToParse, $nCharToPrint+1)" />
						<xsl:with-param name="scr" select="$scr" />
						<xsl:with-param name="sty" select="$sty" />
						<xsl:with-param name="nor" select="$nor" />
					</xsl:call-template>
				</xsl:when>
				
				<!-- Case II: There is an operator at position 1 -->
				<xsl:when test="$fOperAtPos1='1'">
					<mml:mo>
						<xsl:call-template name="CreateTokenAttributes">
							<xsl:with-param name="scr" />
							<xsl:with-param name="sty" />
							<xsl:with-param name="nor" select="$nor" />
							<xsl:with-param name="sTokenType" select="'mo'" />
						</xsl:call-template>
						<xsl:value-of select="substring($sToParse,1,1)" />
					</mml:mo>
					<xsl:call-template name="ParseMt">
						<xsl:with-param name="sToParse" select="substring($sToParse, 2)" />
						<xsl:with-param name="scr" select="$scr" />
						<xsl:with-param name="sty" select="$sty" />
						<xsl:with-param name="nor" select="$nor" />
					</xsl:call-template>
				</xsl:when>
				
				<!-- Case III: There is a number at position 1 -->
				<xsl:otherwise>
					<xsl:variable name="sConsecNum">				
						<xsl:call-template name="SNumStart">
							<xsl:with-param name="sToParse" select="$sToParse" />
							<xsl:with-param name="sPattern" select="$sRepNumWith0" /> 
						</xsl:call-template>
					</xsl:variable>
					<mml:mn>
						<xsl:call-template name="CreateTokenAttributes">
							<xsl:with-param name="scr" select="$scr" />
							<xsl:with-param name="sty" select="'p'" />
							<xsl:with-param name="nor" select="$nor" />
							<xsl:with-param name="sTokenType" select="'mn'" />
						</xsl:call-template>
						<xsl:value-of select="$sConsecNum" />
					</mml:mn>
					<xsl:call-template name="ParseMt">
						<xsl:with-param name="sToParse" select="substring-after($sToParse, $sConsecNum)" />
						<xsl:with-param name="scr" select="$scr" />
						<xsl:with-param name="sty" select="$sty" />
						<xsl:with-param name="nor" select="$nor" />
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:template>
	
	<!-- %%Template: SNumStart 
	
		Return the longest substring of sToParse starting from the 
		start of sToParse that is a number. In addition, it takes the
		pattern string, which is sToParse with all of its numbers 
		replaced with a 0. sPattern should be the same length 
		as sToParse		
	-->
	<xsl:template name="SNumStart">
		<xsl:param name="sToParse" select="''" />
		<xsl:param name="sPattern" select="'$sToParse'"/>	<!-- if we don't get anything, take the string itself -->
				
		<xsl:choose>
			<!-- the pattern says this is a number, recurse with the rest -->
			<xsl:when test="substring($sPattern, 1, 1) = '0'">
				<xsl:call-template name="SNumStart">
					<xsl:with-param name="sToParse" select="$sToParse" />
					<xsl:with-param name="sPattern" select="substring($sPattern, 2)" />
				</xsl:call-template>
			</xsl:when>
			
			<!-- the pattern says we've run out of numbers. Take as many
				characters from sToParse as we shaved off sPattern -->
			<xsl:otherwise>
				<xsl:value-of select="substring($sToParse, 1, string-length($sToParse) - string-length($sPattern))" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<!-- %%Template SRepeatCharAcc
	
			The core of SRepeatChar with an accumulator. The current
			string is in param $acc, and we will double and recurse,
			if we're less than half of the required length or else just 
			add the right amount of characters to the accumulator and
			return
	-->
	<xsl:template name="SRepeatCharAcc">
		<xsl:param name="cchRequired" select="1" />
		<xsl:param name="ch" select="'-'" />
		<xsl:param name="acc" select="$ch" />
		
		<xsl:variable name="cchAcc" select="string-length($acc)" />
		<xsl:choose>
			<xsl:when test="(2 * $cchAcc) &lt; $cchRequired">
				<xsl:call-template name="SRepeatCharAcc">
					<xsl:with-param name="cchRequired" select="$cchRequired" />
					<xsl:with-param name="ch" select="$ch" />
					<xsl:with-param name="acc" select="concat($acc, $acc)" />
				</xsl:call-template>
			</xsl:when>
		
			<xsl:otherwise>
				<xsl:value-of select="concat($acc, substring($acc, 1, $cchRequired - $cchAcc))" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
		
	<!-- %%Template SRepeatChar
	
			Generates a string nchRequired long by repeating the given character ch
	-->
	<xsl:template name="SRepeatChar">
		<xsl:param name="cchRequired" select="1" />
		<xsl:param name="ch" select="'-'" />
		
		<xsl:call-template name="SRepeatCharAcc">
			<xsl:with-param name="cchRequired" select="$cchRequired" />
			<xsl:with-param name="ch" select="$ch" />
			<xsl:with-param name="acc" select="$ch" />
		</xsl:call-template>
	</xsl:template>
	
	<!-- %%Template SReplaceOperWithMinus
	
		Go through the given string and replace every instance
		of an operator with a minus '-'. This helps quickly identify
		the first instance of an operator.  
	-->
	<xsl:template name="SReplaceOperWithMinus">			
		<xsl:param name="sToParse" select="''" />

		<xsl:value-of select="translate($sToParse, $sOperators, $sMinuses)" />			                                
	</xsl:template>
	
	<!-- %%Template SReplaceNumWithZero
	
		Go through the given string and replace every instance
		of an number with a zero '0'. This helps quickly identify
		the first occurence of a number. 
		
		Considers the '.' and ',' part of a number iff they are sandwiched 
		between two other numbers. 0.3 will be recognized as a number,
		x.3 will not be. Since these characters can also be an operator, this 
		should be called before SReplaceOperWithMinus.
	-->
	<xsl:template name="SReplaceNumWithZero">			
		<xsl:param name="sToParse" select="''" />

		<!-- First do a simple replace. Numbers will all be come 0's.
			After this point, the pattern involving the . or , that 
			we are looking for will become 0.0 or 0,0 -->
		<xsl:variable name="sSimpleReplace" select="translate($sToParse, $sNumbers, $sZeros)" />

		<!-- And then, replace 0.0 with just 000. This means that the . will 
			become part of the number -->		
		<xsl:variable name="sReplacePeriod">
			<xsl:call-template name="SReplace">
				<xsl:with-param name="sInput" select="$sSimpleReplace"/>
				<xsl:with-param name="sOrig" select="'0.0'"/>
				<xsl:with-param name="sReplacement" select="'000'"/>			
			</xsl:call-template>
		</xsl:variable>
		
		<!-- And then, replace 0,0 with just 000. This means that the , will 
			become part of the number -->
		<xsl:call-template name="SReplace">
			<xsl:with-param name="sInput" select="$sReplacePeriod"/>
			<xsl:with-param name="sOrig" select="'0,0'"/>
			<xsl:with-param name="sReplacement" select="'000'"/>			
		</xsl:call-template>		
	</xsl:template>	
	
</xsl:stylesheet>
