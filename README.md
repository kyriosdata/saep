# SAEP
Sistema de Apoio à Elaboração de Parecer (SAEP) tem como propósito auxiliar a edição de pareceres de processos de progressão, promoção e avaliação de estágio probatório da Universidade Federal de Goiás (UFG). O SAEP é desenvolvido no contexto da Fábrica de Software do Instituto de Informática (UFG). 

[<img src="https://api.travis-ci.org/kyriosdata/saep.svg?branch=master">](https://travis-ci.org/kyriosdata/saep)
[![Dependency Status](https://www.versioneye.com/user/projects/576c3769cd6d510048bab371/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/576c3769cd6d510048bab371)
[![Coverage Status](https://coveralls.io/repos/github/kyriosdata/saep/badge.svg?branch=master)](https://coveralls.io/github/kyriosdata/saep?branch=master)
[![Sonarqube](https://sonarqube.com/api/badges/gate?key=com.github.kyriosdata.saep:saep-dominio)](https://sonarqube.com/dashboard/index?id=com.github.kyriosdata.saep%3Asaep-dominio)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b4acefbc4abe4cd78c6b77b04a57ab22)](https://www.codacy.com/app/fabio_5/saep?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kyriosdata/saep&amp;utm_campaign=Badge_Grade)
[![Code Climate](https://codeclimate.com/github/kyriosdata/saep/badges/gpa.svg)](https://codeclimate.com/github/kyriosdata/saep)
[![Javadocs](http://javadoc.io/badge/com.github.kyriosdata.saep/saep-dominio.svg)](http://javadoc.io/doc/com.github.kyriosdata.saep/saep-dominio)

O SAEP é feito com o apoio do JProfiler (<a href="http://www.ej-technologies.com/products/jprofiler/overview.html">Java Profiler</a>).
Clique em <img src="https://www.ej-technologies.com/images/product_banners/jprofiler_small.png" height="83" width="24"> para detalhes.

Documentos disponíveis:
* [Termo de Abertura](https://docs.google.com/document/d/1go3eH-8W48G8C6Ryi3bPPN9ZQsbqHNzgrP3ocrnxL2A/edit#heading=h.oxnfirf2m4kr)
* [Visão](https://docs.google.com/document/d/1ElwL9lT6KFeUVl4KvWKZOGROEtLa7Lb2h6L3fLITtyg/edit#heading=h.np717zaohglw) (conceitos de operação)
* [API](http://docs.saep.apiary.io/) (API preliminar de acesso ao SAEP)
* Arquitetura [preliminar](https://docs.google.com/document/d/1pHi74guX42sezCPkEQsQVLkYQOy0Q0Ryk7h1OlD1lyU/edit)
* Resolução 32/2011 [aqui](https://docs.google.com/document/d/18EYL7N0GPqS9rOHXdPQ5amMkQEH65rnuIjLbSo3aMpQ/edit?usp=sharing)
<br />
<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>. 
<br />Fábio Nogueira de Lucena - Fábrica de Software - Instituto de Informática (UFG).

## Como usar?

Basta inserir a dependencia abaixo em seu arquivo pom.xml (maven):

<pre>
&lt;dependency&gt;
  &lt;groupId&gt;com.github.kyriosdata.saep&lt;/groupId&gt;
  &lt;artifactId&gt;saep-dominio&lt;/artifactId&gt;
  &lt;version&gt;1.0.7&lt;/version&gt;
&lt;/dependency&gt;
</pre>
