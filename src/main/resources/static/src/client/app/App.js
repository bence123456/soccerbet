import React, { Component } from 'react'
import ReactDOM from 'react-dom';
import {render} from 'react-dom';
import FlatButton from 'material-ui/FlatButton';
import baseTheme from 'material-ui/styles/baseThemes/lightBaseTheme';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import injectTapEventPlugin from 'react-tap-event-plugin';
injectTapEventPlugin();
import { Router, Route, Link, IndexRoute, hashHistory } from 'react-router'
import { Header, Content, Navigation, Layout } from 'react-mdl/lib/Layout';
import { Footer, FooterLinkList, FooterSection } from 'react-mdl/lib/Footer';

import Home from './Home';
import FinishedMatchResults from './FinishedMatchResults';
import UpcomingMatches from './UpcomingMatches';
import Ranking from './Ranking';
import MyBets from './MyBets';
import NotFound from './NotFound';

const linkStyle = {
	color: 'black',
	marginRight: '6px'
};

class App extends Component {

    getChildContext() {
        return { muiTheme: getMuiTheme(baseTheme) };
    }

    render() {
        return (
            <Router history={hashHistory}>
                <Route path='/' component={Container}>
                    <IndexRoute component={Home} />
                    <Route path='FinishedMatchResults' component={FinishedMatchResults} />
                    <Route path='UpcomingMatches' component={UpcomingMatches} />
                    <Route path='Ranking' component={Ranking} />
                    <Route path='MyBets' component={MyBets} />
                    <Route path='*' component={NotFound} />
                </Route>
            </Router>
        )
    }
}

const Nav = () => (
    <div>
        <Link style={linkStyle} to='/'>Kezdőlap</Link>
        <Link style={linkStyle} to='/finishedmatchresults'>Eredmények</Link>
        <Link style={linkStyle} to='/upcomingmatches'>Tippelés</Link>
        <Link style={linkStyle} to='/ranking'>Ranglista</Link>
        <Link style={linkStyle} to='/mybets'>Fogadásaim</Link>
    </div>
)

const Container = (props) => (
    <div style={{height: '1560px', position: 'relative'}}>
        <Layout style={{background: 'url(/images/4.jpg) center / cover'}}>
            <Header transparent title={<FlatButton label="DFB-Pokal Tippjáték" containerElement={<Link to="/"/>} style={{color: 'green'}} />} style={{color: 'green'}}>
                <Navigation>
                    <Nav />
                </Navigation>
            </Header>
            <Content>
                {props.children}
            </Content>
            <Footer size="mini">
                <FooterSection type="middle">
                    <FooterLinkList>
                        <FooterLinks />
                    </FooterLinkList>
                </FooterSection>
            </Footer>
        </Layout>
    </div>
)

const FooterLinks = () => (
    <table>
        <tbody>
            <tr>
                <td>
                    <a href="http://www.dfb.de/dfb-pokal/start/">
                        <img src="/images/dfb-pokal.png" alt="DFB-Pokal" width="30" height="30" />
                        <br/> A DFB-Pokal Hivatalos oldala
                    </a>
                </td>
                <td>
                    <a href="https://github.com/bence123456/soccerbet">
                        <img src="/images/github.png" alt="GitHub" width="60" height="30" />
                        <br/> GitHub Link
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
)

App.childContextTypes = {
    muiTheme: React.PropTypes.object.isRequired,
};

ReactDOM.render(<App />, document.getElementById('app'));