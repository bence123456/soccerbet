'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {teams: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/teams'}).done(response => {
			this.setState({teams: response.entity._embedded.teams});
		});
	}

	render() {
		return (
			<TeamList teams={this.state.teams}/>
		)
	}
}

class TeamList extends React.Component{
	render() {
		var teams = this.props.teams.map(team =>
			<Team key={team._links.self.href} team={team}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
					</tr>
					{teams}
				</tbody>
			</table>
		)
	}
}

class Team extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.team.name}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)